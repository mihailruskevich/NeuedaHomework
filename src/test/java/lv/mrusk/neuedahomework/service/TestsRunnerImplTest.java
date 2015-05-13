package lv.mrusk.neuedahomework.service;

import lv.mrusk.neuedahomework.databuilder.RequestBuilder;
import lv.mrusk.neuedahomework.exception.MethodNotFoundException;
import lv.mrusk.neuedahomework.exception.UnsupportedHttpMethodException;
import lv.mrusk.neuedahomework.model.tests.Parameter;
import lv.mrusk.neuedahomework.model.tests.Request;
import lv.mrusk.neuedahomework.testdata.TestParameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestsRunnerImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TestsRunnerImpl testsRunner;

    @Test
    public void notEquals_ShouldReturnTrueForNotEqualStrings() {
        assertTrue(testsRunner.notEquals("1", "12"));
    }

    @Test
    public void notEquals_ShouldReturnFalseForEqualStrings() {
        assertFalse(testsRunner.notEquals("2", "2"));
    }

    @Test
    public void createParametrizedUrl_ShouldCorrectlyCreateUrl() {
        String expected = "http://calculator.neueda.lv/api/add?a=1&b=2";

        String actual = testsRunner.createParametrizedUrl("http://calculator.neueda.lv", "/api/add", TestParameter.aRegularParameterList());

        assertEquals(expected, actual);
    }

    @Test(expected = MethodNotFoundException.class)
    public void createParametrizedUrl_ShouldThrowMethodNotFoundExceptionForUrlWithIllegalSymbols() {
        testsRunner.createParametrizedUrl("http://calculator.neueda.lv<", "/api/add", Collections.emptyList());
    }

    @Test
    public void createMultiValueMap_ShouldCorrectlyCreateMultiValueMap() {
        MultiValueMap<String, String> expected = TestParameter.aRegularParameterMultiValueMap();
        MultiValueMap<String, String> actual = testsRunner.createMultiValueMap(TestParameter.aRegularParameterList());

        assertEquals(expected, actual);
    }

    @Test
    public void queryForResponse_ShouldCallCreateParametrizedUrlAndGetForObjectWithCorrectParametersForGetMethod() {
        String apiUrl = "http://calculator.neueda.lv";
        String apiPath = "/api/add";
        String urlWithParameters = "http://calculator.neueda.lv/api/add?a=1&b=2";
        Request request = RequestBuilder.aRequest()
                .withMethod(Request.GET)
                .withPath(apiPath)
                .build();

        List<Parameter> parameterList = TestParameter.aRegularParameterList();
        TestsRunnerImpl testsRunnerSpy = spy(testsRunner);
        testsRunnerSpy.queryForResponse(apiUrl, request, parameterList);

        verify(testsRunnerSpy).createParametrizedUrl(apiUrl, apiPath, parameterList);
        verify(restTemplate).getForObject(urlWithParameters, String.class);
    }

    @Test
    public void queryForResponse_ShouldCallPostForObjectWithCorrectParametersForPostMethod() {
        String apiUrl = "http://calculator.neueda.lv";
        String apiPath = "/api/add";
        Request request = RequestBuilder.aRequest()
                .withMethod(Request.POST)
                .withPath(apiPath)
                .build();

        List<Parameter> parameterList = TestParameter.aRegularParameterList();
        MultiValueMap<String, String> parameterMap = TestParameter.aRegularParameterMultiValueMap();
        TestsRunnerImpl testsRunnerSpy = spy(testsRunner);
        testsRunnerSpy.queryForResponse(apiUrl, request, parameterList);

        verify(restTemplate).postForObject(apiUrl, parameterMap, String.class);
    }

    @Test(expected = UnsupportedHttpMethodException.class)
    public void queryForResponse_ShouldThrowUnsupportedHttpMethodExceptionForUnsupportedMethod() {
        Request request = RequestBuilder.aRequest()
                .withMethod("DELETE")
                .withPath("/api/add")
                .build();

        List<Parameter> parameterList = TestParameter.aRegularParameterList();
        testsRunner.queryForResponse("http://calculator.neueda.lv", request, parameterList);
    }

}
