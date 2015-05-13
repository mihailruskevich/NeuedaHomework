package lv.mrusk.neuedahomework.service;

import lv.mrusk.neuedahomework.model.tests.TestBundle;
import lv.mrusk.neuedahomework.model.tests.TestResult;

import java.util.List;

public interface TestsRunner {
    List<TestResult> runTests(TestBundle tests, String apiUrl);
}
