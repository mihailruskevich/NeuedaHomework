package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.databuilder.TestBundleBuilder;
import lv.mrusk.neuedahomework.model.tests.TestBundle;

import java.util.Collections;

import static java.util.Arrays.asList;

public class TestTestBundle {

    public static TestBundle aRegularTestBundle() {
        return TestBundleBuilder.aTestBundle()
                .withTestsName("Calc tests")
                .withTestSuiteList(asList(TestTestSuite.aRegularTestSuite()))
                .build();
    }

    public static TestBundle aTestBundleWithoutTestSuiteList() {
        return TestBundleBuilder.aTestBundle()
                .withTestsName("Calc tests")
                .withTestSuiteList(Collections.emptyList())
                .build();
    }
}
