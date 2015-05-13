package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.databuilder.TestCaseBuilder;
import lv.mrusk.neuedahomework.model.tests.TestCase;

import java.util.Collections;

import static java.util.Arrays.asList;

public class TestTestCase {

    public static TestCase aRegularTestCase() {
        return TestCaseBuilder.aTestCase()
                .withTestCaseName("simple addition")
                .withExpectedResult(TestParameter.aRegularResultParameter())
                .withParameterList(asList(TestParameter.aRegularAParameter(), TestParameter.aRegularBParameter()))
                .build();
    }

    public static TestCase aTestCaseWithoutParameterList() {
        return TestCaseBuilder.aTestCase()
                .withTestCaseName("simple addition")
                .withExpectedResult(TestParameter.aRegularResultParameter())
                .withParameterList(Collections.emptyList())
                .build();
    }


}
