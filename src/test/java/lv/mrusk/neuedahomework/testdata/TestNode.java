package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.databuilder.NodeBuilder;
import lv.mrusk.neuedahomework.model.mindmap.Node;

import static java.util.Arrays.asList;

public class TestNode {

    public static Node aRegularAParamNode() {
        return NodeBuilder.aNode()
                .withText("a: 1")
                .build();
    }

    public static Node aRegularBParamNode() {
        return NodeBuilder.aNode()
                .withText("b: 2")
                .build();
    }

    public static Node aRegularParamListNode() {
        return NodeBuilder.aNode()
                .withText("params")
                .withChildNodes(asList(aRegularAParamNode(), aRegularBParamNode()))
                .build();
    }

    public static Node aRegularResultNode() {
        return NodeBuilder.aNode()
                .withText("result: 3")
                .build();
    }

    public static Node aRegularTestCaseNode() {
        return NodeBuilder.aNode()
                .withText("simple addition")
                .withChildNodes(asList(aRegularResultNode(), aRegularParamListNode()))
                .build();
    }

    public static Node aRegularTestCaseListNode(){
        return NodeBuilder.aNode()
                .withText("Test suite")
                .withChildNodes(asList(aRegularTestCaseNode()))
                .build();
    }

    public static Node aRegularMethodNode() {
        return NodeBuilder.aNode()
                .withText("Method: GET")
                .build();
    }

    public static Node aRegularPathNode() {
        return NodeBuilder.aNode()
                .withText("Path: /api/add")
                .build();
    }

    public static Node aRegularRequestNode() {
        return NodeBuilder.aNode()
                .withText("Request")
                .withChildNodes(asList(aRegularMethodNode(), aRegularPathNode()))
                .build();
    }



    public static Node aRegularTestSuiteNode() {
        return NodeBuilder.aNode()
                .withText("Add")
                .withChildNodes(asList(aRegularRequestNode(), aRegularTestCaseListNode()))
                .build();
    }

    public static Node aRegularTestBundleNode() {
        return NodeBuilder.aNode()
                .withText("Calc tests")
                .withChildNodes(asList(aRegularTestSuiteNode()))
                .build();
    }


    public static Node aTestBundleNodeWithoutChildNodes() {
        return NodeBuilder.aNode()
                .withText("Calc tests")
                .build();
    }

    public static Node aNodeWithoutChildList() {
        return NodeBuilder.aNode()
                .withText("Node")
                .build();
    }

    public static Node aTestSuiteNodeWithoutRequestNode() {
        return NodeBuilder.aNode()
                .withText("Add")
                .withChildNodes(asList(aRegularTestCaseListNode()))
                .build();
    }

    public static Node aTestCaseListNodeWithoutTestCases(){
        return NodeBuilder.aNode()
                .withText("Test suite")
                .build();
    }

    public static Node aTestSuiteNodeWithoutTestCaseNodesList() {
        return NodeBuilder.aNode()
                .withText("Add")
                .withChildNodes(asList(aTestCaseListNodeWithoutTestCases(), aRegularRequestNode()))
                .build();
    }

    public static Node aRequestNodeWithoutMethodNode() {
        return NodeBuilder.aNode()
                .withText("Request")
                .withChildNodes(asList(aRegularPathNode()))
                .build();
    }

    public static Node aTestCaseNodeWithoutResultNode() {
        return NodeBuilder.aNode()
                .withText("simple addition")
                .withChildNodes(asList(aRegularParamListNode()))
                .build();
    }

    public static Node aParamListNodeWithoutParameters() {
        return NodeBuilder.aNode()
                .withText("params")
                .build();
    }

    public static Node aTestCaseNodeWithoutParameterNodeList() {
        return NodeBuilder.aNode()
                .withText("simple addition")
                .withChildNodes(asList(aRegularResultNode(), aParamListNodeWithoutParameters()))
                .build();
    }

}
