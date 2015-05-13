package lv.mrusk.neuedahomework.service;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface Unmarshaler {
    <T> T unmarshal(Class<? extends T> entryClass, File file) throws JAXBException;
}
