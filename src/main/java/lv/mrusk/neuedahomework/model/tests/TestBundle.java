package lv.mrusk.neuedahomework.model.tests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class TestBundle {

    private String testsName;
    private List<TestSuite> testSuiteList;

    public String getTestsName() {
        return testsName;
    }

    public void setTestsName(String testsName) {
        this.testsName = testsName;
    }

    public List<TestSuite> getTestSuiteList() {
        return testSuiteList;
    }

    public void setTestSuiteList(List<TestSuite> testSuiteList) {
        this.testSuiteList = testSuiteList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestBundle that = (TestBundle) o;

        if (testSuiteList != null ? !testSuiteList.equals(that.testSuiteList) : that.testSuiteList != null)
            return false;
        if (testsName != null ? !testsName.equals(that.testsName) : that.testsName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testsName != null ? testsName.hashCode() : 0;
        result = 31 * result + (testSuiteList != null ? testSuiteList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("testsName", testsName)
                .append("testSuiteList", testSuiteList)
                .toString();
    }
}
