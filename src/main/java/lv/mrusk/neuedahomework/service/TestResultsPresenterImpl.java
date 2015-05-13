package lv.mrusk.neuedahomework.service;


import lv.mrusk.neuedahomework.model.tests.TestResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TestResultsPresenterImpl implements TestResultsPresenter {

    private Logger log = Logger.getLogger(getClass());

    public static final String SEPARATOR = " | ";
    public static final String TEST = "Test";
    public static final String PATH = "path: ";
    public static final String METHOD = "method: ";
    public static final String PARAMETERS = "parameters: ";
    public static final String ACTUAL = "actual: ";
    public static final String EXPECTED = "expected: ";
    public static final String FAILED = "FAILED";
    public static final String OK = "OK";

    public String convertStatus(boolean isTestFailed) {
        return isTestFailed ? FAILED : OK;
    }

    public String buildTestResult(TestResult testResult) {
        StringBuilder testResultBuilder = new StringBuilder();
        testResultBuilder.append(TEST);
        testResultBuilder.append(SEPARATOR);
        testResultBuilder.append(PATH);
        testResultBuilder.append(testResult.getMethodPath());
        testResultBuilder.append(SEPARATOR);
        testResultBuilder.append(METHOD);
        testResultBuilder.append(testResult.getMethodType());
        testResultBuilder.append(SEPARATOR);
        testResultBuilder.append(PARAMETERS);
        testResultBuilder.append(testResult.getParameterList());
        testResultBuilder.append(SEPARATOR);
        testResultBuilder.append(ACTUAL);
        testResultBuilder.append(testResult.getActualResult());
        testResultBuilder.append(SEPARATOR);
        testResultBuilder.append(EXPECTED);
        testResultBuilder.append(testResult.getExpectedResult());
        testResultBuilder.append(SEPARATOR);
        testResultBuilder.append(convertStatus(testResult.isFailed()));
        return testResultBuilder.toString();
    }

    @Override
    public void presentTestResult(TestResult testResult) {
        log.info(buildTestResult(testResult));
    }

}
