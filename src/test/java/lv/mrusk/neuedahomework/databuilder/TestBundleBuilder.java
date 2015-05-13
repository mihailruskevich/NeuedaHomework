package lv.mrusk.neuedahomework.databuilder;

import lv.mrusk.neuedahomework.model.tests.TestBundle;
import lv.mrusk.neuedahomework.model.tests.TestSuite;
import org.apache.commons.lang3.builder.Builder;

import java.util.List;

public class TestBundleBuilder implements Builder<TestBundle> {

    private String testsName;
    private List<TestSuite> testSuiteList;

    private TestBundleBuilder() {
    }

    @Override
    public TestBundle build() {
        TestBundle testBundle = new TestBundle();
        testBundle.setTestsName(testsName);
        testBundle.setTestSuiteList(testSuiteList);
        return testBundle;
    }

    public static TestBundleBuilder aTestBundle() {
        return new TestBundleBuilder();
    }

    public TestBundleBuilder withTestsName(String testsName) {
        this.testsName = testsName;
        return this;
    }

    public TestBundleBuilder withTestSuiteList(List<TestSuite> testSuiteList) {
        this.testSuiteList = testSuiteList;
        return this;
    }
}
