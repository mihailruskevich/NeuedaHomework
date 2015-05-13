package lv.mrusk.neuedahomework.validation;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class MindMapFileParameterValidator implements IParameterValidator {

    public static final String MIND_MAP_FILE_EXTENSION = "mm";

    @Override
    public void validate(String name, String value) throws ParameterException {
        File file = new File(value);
        if (!file.isFile() || !MIND_MAP_FILE_EXTENSION.equals(FilenameUtils.getExtension(value))) {
            throw new ParameterException(value + " isn't a *.mm file or it doesn't exists");
        }
    }
}
