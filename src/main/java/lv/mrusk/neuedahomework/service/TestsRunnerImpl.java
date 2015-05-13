package lv.mrusk.neuedahomework.service;

import com.jayway.jsonpath.JsonPath;
import lv.mrusk.neuedahomework.exception.MethodNotFoundException;
import lv.mrusk.neuedahomework.exception.UnsupportedHttpMethodException;
import lv.mrusk.neuedahomework.model.tests.*;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestsRunnerImpl implements TestsRunner {

    @Autowired
    private RestTemplate restTemplate;

    public boolean notEquals(String expectedResult, String actualResult) {
        return !actualResult.equals(expectedResult);
    }

    public String parseActualResult(TestCase testCase, String resultJson) {
        return JsonPath.read(resultJson, testCase.getExpectedResult().getKey());
    }

    public String createParametrizedUrl(String apiUrl, String apiPath, List<Parameter> parameterList) {
        try {
            URIBuilder uriBuilder = new URIBuilder(apiUrl + apiPath);
            parameterList.stream()
                    .forEach(paramEntry -> uriBuilder.addParameter(paramEntry.getKey(), paramEntry.getValue()));
            return uriBuilder.toString();
        } catch (URISyntaxException e) {
            throw new MethodNotFoundException("Incorrect url format: " + apiUrl + apiPath);
        }
    }

    public MultiValueMap<String, String> createMultiValueMap(List<Parameter> parameterList) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        parameterList.stream()
                .forEach(parameter -> map.add(parameter.getKey(), parameter.getValue()));
        return map;
    }

    public String queryForResponse(String apiUrl, Request request, List<Parameter> parameterList) {
        switch (request.getMethod()) {
            case Request.GET:
                String parametrizedUrl = createParametrizedUrl(apiUrl, request.getPath(), parameterList);
                return restTemplate.getForObject(parametrizedUrl, String.class);
            case Request.POST:
                return restTemplate.postForObject(apiUrl, createMultiValueMap(parameterList), String.class);
            default:
                throw new UnsupportedHttpMethodException("Unsupported method type: " + request.getMethod());
        }
    }

    public String queryForActualResult(TestCase testCase, Request request, String apiUrl) {
        try {
            return queryForResponse(apiUrl, request, testCase.getParameterList());
        } catch (HttpClientErrorException e) {
            throw new MethodNotFoundException(request.getMethod() + " method: " + request.getPath() + " not found for parameters: " + testCase.getParameterList());
        }

    }

    public TestResult runTestCase(TestCase testCase, Request request, String apiUrl) {
        String actualResultJson = queryForActualResult(testCase, request, apiUrl);
        String actualResult = parseActualResult(testCase, actualResultJson);

        TestResult testResult = new TestResult();
        testResult.setMethodPath(request.getPath());
        testResult.setMethodType(request.getMethod());
        testResult.setActualResult(actualResult);
        testResult.setExpectedResult(testCase.getExpectedResult().getValue());
        testResult.setParameterList(testCase.getParameterList());
        testResult.setFailed(notEquals(testCase.getExpectedResult().getValue(), actualResult));
        return testResult;
    }

    public List<TestResult> runTestSuite(TestSuite testSuite, String apiUrl) {
        return testSuite.getTestCaseList().parallelStream()
                .map(testCase -> runTestCase(testCase, testSuite.getRequest(), apiUrl))
                .collect(Collectors.toList());
    }

    @Override
    public List<TestResult> runTests(TestBundle tests, String apiUrl) {
        List<TestResult> testResultList = new ArrayList<>();
        tests.getTestSuiteList().parallelStream()
                .forEach(testSuite -> testResultList.addAll(runTestSuite(testSuite, apiUrl)));
        return testResultList;
    }
}
