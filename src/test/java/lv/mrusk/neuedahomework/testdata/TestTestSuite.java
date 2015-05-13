package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.databuilder.TestSuiteBuilder;
import lv.mrusk.neuedahomework.model.tests.TestSuite;

import static java.util.Arrays.asList;

public class TestTestSuite {

    public static TestSuite aRegularTestSuite() {
        return TestSuiteBuilder.aTestSuite()
                .withTestSuiteName("Add")
                .withRequest(TestRequest.aRegularRequest())
                .withTestCaseList(asList(TestTestCase.aRegularTestCase()))
                .build();
    }
}
