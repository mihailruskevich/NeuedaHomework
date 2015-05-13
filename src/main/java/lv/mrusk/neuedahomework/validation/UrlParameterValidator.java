package lv.mrusk.neuedahomework.validation;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.net.URL;

public class UrlParameterValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        try {
            URL url = new URL(value);
            url.openConnection().connect();
        } catch (IOException e) {
            throw new ParameterException("Unknown host: " + e.getMessage());
        }
    }
}
