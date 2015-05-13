package lv.mrusk.neuedahomework.model.tests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class TestCase {

    private String testCaseName;
    private List<Parameter> parameterList;
    private Parameter expectedResult;

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }

    public Parameter getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(Parameter expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        if (expectedResult != null ? !expectedResult.equals(testCase.expectedResult) : testCase.expectedResult != null)
            return false;
        if (parameterList != null ? !parameterList.equals(testCase.parameterList) : testCase.parameterList != null)
            return false;
        if (testCaseName != null ? !testCaseName.equals(testCase.testCaseName) : testCase.testCaseName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testCaseName != null ? testCaseName.hashCode() : 0;
        result = 31 * result + (parameterList != null ? parameterList.hashCode() : 0);
        result = 31 * result + (expectedResult != null ? expectedResult.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("testCaseName", testCaseName)
                .append("parameterList", parameterList)
                .append("expectedResult", expectedResult)
                .toString();
    }
}
