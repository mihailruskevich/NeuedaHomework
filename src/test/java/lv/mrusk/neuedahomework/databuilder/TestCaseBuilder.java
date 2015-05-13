package lv.mrusk.neuedahomework.databuilder;

import lv.mrusk.neuedahomework.model.tests.Parameter;
import lv.mrusk.neuedahomework.model.tests.TestCase;
import org.apache.commons.lang3.builder.Builder;

import java.util.List;

public class TestCaseBuilder implements Builder<TestCase> {

    private String testCaseName;
    private List<Parameter> parameterList;
    private Parameter expectedResult;

    private TestCaseBuilder() {
    }

    @Override
    public TestCase build() {
        TestCase testCase = new TestCase();
        testCase.setTestCaseName(testCaseName);
        testCase.setParameterList(parameterList);
        testCase.setExpectedResult(expectedResult);
        return testCase;
    }

    public static TestCaseBuilder aTestCase() {
        return new TestCaseBuilder();
    }

    public TestCaseBuilder withTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
        return this;
    }

    public TestCaseBuilder withParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
        return this;
    }

    public TestCaseBuilder withExpectedResult(Parameter expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }
}
