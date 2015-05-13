package lv.mrusk.neuedahomework.converter;

import lv.mrusk.neuedahomework.exception.MindMapFormatException;
import lv.mrusk.neuedahomework.model.mindmap.MindMap;
import lv.mrusk.neuedahomework.model.mindmap.Node;
import lv.mrusk.neuedahomework.model.tests.*;
import org.apache.commons.collections4.CollectionUtils;
import org.dozer.CustomConverter;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MindMapToTestsConverter implements CustomConverter {

    public static final String PARAMS_NODE_REGEX = "(^[a-zA-Z]+): (.+)$";
    public static final Pattern PARAMS_NODE_PATTERN = Pattern.compile(PARAMS_NODE_REGEX);
    public static final int PARAMETER_NAME_GROUP_INDEX = 1;
    public static final int PARAMETER_VALUE_GROUP_INDEX = 2;

    public static final String RESULT_NODE_PREFIX = "result:";
    public static final String METHOD_NODE_PREFIX = "Method:";
    public static final String PATH_NODE_PREFIX = "Path:";

    public static final String PARAMS_NODE_TEXT = "params";
    public static final String TEST_SUITE_NODE_TEXT = "Test suite";
    public static final String REQUEST_NODE_TEXT = "Request";

    public Parameter parseNodeText(String text) {
        Matcher matcher = PARAMS_NODE_PATTERN.matcher(text);
        if (matcher.find()) {
            Parameter parameter = new Parameter();
            parameter.setKey(matcher.group(PARAMETER_NAME_GROUP_INDEX));
            parameter.setValue(matcher.group(PARAMETER_VALUE_GROUP_INDEX));
            return parameter;
        } else {
            throw new MindMapFormatException("Format error in node: " + text);
        }

    }

    public List<Parameter> parseParamsNodesText(List<Node> paramsNodeList) {
        return paramsNodeList.stream()
                .map((node) -> parseNodeText(node.getText()))
                .collect(Collectors.toList());
    }

    public Optional<Node> findNode(List<Node> nodeList, Predicate<Node> filterPredicate) {
        return nodeList.stream()
                .filter(filterPredicate)
                .findFirst();
    }

    public TestCase createTestCase(Node testCaseNode) {
        TestCase testCase = new TestCase();
        testCase.setTestCaseName(testCaseNode.getText());

        List<Node> paramsOrResultNodeList = testCaseNode.getChildNodes();
        if (CollectionUtils.isEmpty(paramsOrResultNodeList)) {
            throw new MindMapFormatException("Params and result node not found in test case: " + testCaseNode.getText());
        }

        Optional<Node> resultNode = findNode(paramsOrResultNodeList, (node) -> node.getText().startsWith(RESULT_NODE_PREFIX));
        if (resultNode.isPresent()) {
            Parameter resultNodeParameter = parseNodeText(resultNode.get().getText());
            testCase.setExpectedResult(resultNodeParameter);
        } else {
            throw new MindMapFormatException("Result node not found in test case: " + testCaseNode.getText());
        }

        Optional<Node> paramsNode = findNode(paramsOrResultNodeList, (node) -> PARAMS_NODE_TEXT.equals(node.getText()));
        if (paramsNode.isPresent()) {
            List<Node> paramsList = paramsNode.get().getChildNodes();
            if (!CollectionUtils.isEmpty(paramsList)) {
                testCase.setParameterList(parseParamsNodesText(paramsList));
            } else {
                testCase.setParameterList(Collections.emptyList());
            }
        } else {
            throw new MindMapFormatException("Params node not found in test case: " + testCaseNode.getText());
        }

        return testCase;
    }


    public Request createRequest(Node requestNode) {
        Request request = new Request();

        List<Node> methodOrPathNodeList = requestNode.getChildNodes();
        if (CollectionUtils.isEmpty(methodOrPathNodeList)) {
            throw new MindMapFormatException("Method and Path not found in request");
        }

        Optional<Node> methodNode = findNode(methodOrPathNodeList, (node) -> node.getText().startsWith(METHOD_NODE_PREFIX));
        if (methodNode.isPresent()) {
            Parameter methodNodeParameter = parseNodeText(methodNode.get().getText());
            request.setMethod(methodNodeParameter.getValue());
        } else {
            throw new MindMapFormatException("Method node not found in request");
        }

        Optional<Node> pathNode = findNode(methodOrPathNodeList, (node) -> node.getText().startsWith(PATH_NODE_PREFIX));
        if (pathNode.isPresent()) {
            Parameter pathNodeParameter = parseNodeText(pathNode.get().getText());
            request.setPath(pathNodeParameter.getValue());
        } else {
            throw new MindMapFormatException("Path node not found in request");
        }

        return request;
    }

    public List<TestCase> createTestCaseList(List<Node> testCaseNodeList) {
        return testCaseNodeList
                .parallelStream()
                .map(this::createTestCase)
                .collect(Collectors.toList());
    }

    public TestSuite createTestSuite(Node operationNode) {
        TestSuite testSuite = new TestSuite();
        testSuite.setTestSuiteName(operationNode.getText());

        List<Node> requestOrTestSuiteNodeList = operationNode.getChildNodes();
        if (CollectionUtils.isEmpty(requestOrTestSuiteNodeList)) {
            throw new MindMapFormatException("Request and Test suite not found for operation: " + operationNode.getText());
        }

        Optional<Node> requestNode = findNode(requestOrTestSuiteNodeList, (node) -> REQUEST_NODE_TEXT.equals(node.getText()));
        if (requestNode.isPresent()) {
            testSuite.setRequest(createRequest(requestNode.get()));
        } else {
            throw new MindMapFormatException("Request node not found for operation: " + operationNode.getText());
        }

        Optional<Node> testSuiteNode = findNode(requestOrTestSuiteNodeList, (node) -> TEST_SUITE_NODE_TEXT.equals(node.getText()));
        if (testSuiteNode.isPresent()) {
            List<Node> testCaseNodeList = testSuiteNode.get().getChildNodes();
            if (CollectionUtils.isEmpty(testCaseNodeList)) {
                throw new MindMapFormatException("There should one ore more test cases for operation: " + operationNode.getText());
            }
            testSuite.setTestCaseList(createTestCaseList(testCaseNodeList));
        } else {
            throw new MindMapFormatException("Test suite node not found for operation: " + operationNode.getText());
        }

        return testSuite;
    }

    public List<TestSuite> createTestSuiteList(List<Node> testSuiteNodeList) {
        return testSuiteNodeList
                .parallelStream()
                .map(this::createTestSuite)
                .collect(Collectors.toList());
    }

    public TestBundle createTestsFromMindMap(MindMap mindMap) {
        Node testsNode = mindMap.getNode();
        List<Node> testSuiteNodeList = testsNode.getChildNodes();
        TestBundle tests = new TestBundle();
        tests.setTestsName(testsNode.getText());
        if (!CollectionUtils.isEmpty(testSuiteNodeList)) {
            tests.setTestSuiteList(createTestSuiteList(testSuiteNodeList));
        } else {
            tests.setTestSuiteList(Collections.emptyList());
        }
        return tests;
    }

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        MindMap mindMap = (MindMap) source;
        return createTestsFromMindMap(mindMap);
    }
}
