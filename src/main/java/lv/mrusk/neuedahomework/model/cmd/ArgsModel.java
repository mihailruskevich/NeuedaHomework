package lv.mrusk.neuedahomework.model.cmd;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.beust.jcommander.converters.URLConverter;
import lv.mrusk.neuedahomework.validation.MindMapFileParameterValidator;
import lv.mrusk.neuedahomework.validation.UrlParameterValidator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;
import java.net.URL;

public class ArgsModel {

    @Parameter(
            names = "-f",
            description = "Mind map file name",
            converter = FileConverter.class,
            validateWith = MindMapFileParameterValidator.class,
            required = true
    )
    private File file;

    @Parameter(
            names = "-u",
            description = "Rest api endpoint url",
            converter = URLConverter.class,
            validateWith = UrlParameterValidator.class,
            required = true
    )
    private URL apiUrl;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public URL getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(URL apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("file", file)
                .append("apiUrl", apiUrl)
                .toString();
    }
}
