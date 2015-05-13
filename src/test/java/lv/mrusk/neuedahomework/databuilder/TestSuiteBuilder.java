package lv.mrusk.neuedahomework.databuilder;

import lv.mrusk.neuedahomework.model.tests.Request;
import lv.mrusk.neuedahomework.model.tests.TestCase;
import lv.mrusk.neuedahomework.model.tests.TestSuite;
import org.apache.commons.lang3.builder.Builder;

import java.util.List;

public class TestSuiteBuilder implements Builder<TestSuite> {

    private String testSuiteName;
    private Request request;
    private List<TestCase> testCaseList;

    private TestSuiteBuilder() {
    }

    @Override
    public TestSuite build() {
        TestSuite testSuite = new TestSuite();
        testSuite.setTestSuiteName(testSuiteName);
        testSuite.setRequest(request);
        testSuite.setTestCaseList(testCaseList);
        return testSuite;
    }

    public static TestSuiteBuilder aTestSuite() {
        return new TestSuiteBuilder();
    }

    public TestSuiteBuilder withTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
        return this;
    }

    public TestSuiteBuilder withRequest(Request request) {
        this.request = request;
        return this;
    }

    public TestSuiteBuilder withTestCaseList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
        return this;
    }


}
