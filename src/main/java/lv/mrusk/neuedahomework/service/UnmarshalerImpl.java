package lv.mrusk.neuedahomework.service;

import lv.mrusk.neuedahomework.service.Unmarshaler;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Service
public class UnmarshalerImpl implements Unmarshaler {

    @Override
    public <T> T unmarshal(Class<? extends T> entryClass, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(entryClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(file);
    }
}
