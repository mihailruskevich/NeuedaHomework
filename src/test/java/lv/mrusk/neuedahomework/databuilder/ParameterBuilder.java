package lv.mrusk.neuedahomework.databuilder;

import lv.mrusk.neuedahomework.model.tests.Parameter;
import org.apache.commons.lang3.builder.Builder;

public class ParameterBuilder implements Builder<Parameter> {

    private String key;
    private String value;

    private ParameterBuilder() {
    }

    @Override
    public Parameter build() {
        Parameter parameter = new Parameter();
        parameter.setKey(key);
        parameter.setValue(value);
        return parameter;
    }

    public static ParameterBuilder aParameter() {
        return new ParameterBuilder();
    }

    public ParameterBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public ParameterBuilder withValue(String value) {
        this.value = value;
        return this;
    }
}
