package lv.mrusk.neuedahomework.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestResultsPresenterImplTest {

    private TestResultsPresenterImpl testResultsPresenter = new TestResultsPresenterImpl();

    @Test
    public void convertStatus_ShouldReturnOkForFalse() {
        String expected = TestResultsPresenterImpl.OK;
        String actual = testResultsPresenter.convertStatus(false);
        assertEquals(expected, actual);
    }

    @Test
    public void convertStatus_ShouldReturnFailedForTrue() {
        String expected = TestResultsPresenterImpl.FAILED;
        String actual = testResultsPresenter.convertStatus(true);
        assertEquals(expected, actual);
    }
}
