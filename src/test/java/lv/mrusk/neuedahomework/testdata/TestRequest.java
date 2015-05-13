package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.databuilder.RequestBuilder;
import lv.mrusk.neuedahomework.model.tests.Request;

public class TestRequest {

    public static Request aRegularRequest() {
        return RequestBuilder.aRequest()
                .withMethod(Request.GET)
                .withPath("/api/add")
                .build();
    }
}
