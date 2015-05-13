package lv.mrusk.neuedahomework.model.tests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class TestSuite {

    private String testSuiteName;
    private Request request;
    private List<TestCase> testCaseList;

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestSuite testSuite = (TestSuite) o;

        if (request != null ? !request.equals(testSuite.request) : testSuite.request != null) return false;
        if (testCaseList != null ? !testCaseList.equals(testSuite.testCaseList) : testSuite.testCaseList != null)
            return false;
        if (testSuiteName != null ? !testSuiteName.equals(testSuite.testSuiteName) : testSuite.testSuiteName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testSuiteName != null ? testSuiteName.hashCode() : 0;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        result = 31 * result + (testCaseList != null ? testCaseList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("testSuiteName", testSuiteName)
                .append("request", request)
                .append("testCaseList", testCaseList)
                .toString();
    }
}
