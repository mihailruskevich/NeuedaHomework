package lv.mrusk.neuedahomework.databuilder;

import lv.mrusk.neuedahomework.model.tests.Request;
import org.apache.commons.lang3.builder.Builder;

public class RequestBuilder implements Builder<Request>{

    private String method;
    private String path;

    private RequestBuilder() {
    }

    @Override
    public Request build() {
        Request request = new Request();
        request.setMethod(method);
        request.setPath(path);
        return request;
    }

    public static RequestBuilder aRequest() {
        return new RequestBuilder();
    }

    public RequestBuilder withMethod(String method) {
        this.method = method;
        return this;
    }

    public RequestBuilder withPath(String path) {
        this.path = path;
        return this;
    }
}
