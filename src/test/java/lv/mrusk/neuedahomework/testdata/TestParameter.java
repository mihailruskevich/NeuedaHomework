package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.databuilder.ParameterBuilder;
import lv.mrusk.neuedahomework.model.tests.Parameter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static java.util.Arrays.asList;

public class TestParameter {

    public static Parameter aRegularResultParameter() {
        return ParameterBuilder.aParameter()
                .withKey("result")
                .withValue("3")
                .build();
    }

    public static Parameter aRegularAParameter() {
        return ParameterBuilder.aParameter()
                .withKey("a")
                .withValue("1")
                .build();
    }

    public static Parameter aRegularBParameter() {
        return ParameterBuilder.aParameter()
                .withKey("b")
                .withValue("2")
                .build();
    }



    public static List<Parameter> aRegularParameterList() {
        return asList(aRegularAParameter(), aRegularBParameter());
    }

    public static MultiValueMap<String, String> aRegularParameterMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("a", "1");
        map.add("b", "2");
        return map;
    }
}
