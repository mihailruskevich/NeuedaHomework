package lv.mrusk.neuedahomework.service;


import lv.mrusk.neuedahomework.model.tests.TestResult;

import java.util.List;

public interface TestResultsPresenter {

    void presentTestResult(TestResult testResult);

    default void presentTestResultList(List<TestResult> testResultList) {
        testResultList.parallelStream()
                .forEach(this::presentTestResult);
    }
}
