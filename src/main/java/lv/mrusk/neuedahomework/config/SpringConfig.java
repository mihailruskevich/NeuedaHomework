package lv.mrusk.neuedahomework.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@ComponentScan({"lv.mrusk.neuedahomework.service"})
public class SpringConfig {

    public static final String DOZER_MAPPING_FILE_NAME = "dozerBeanMapping.xml";

    @Bean
    public Mapper dozerBeanMapper() {
        return new DozerBeanMapper(Arrays.asList(DOZER_MAPPING_FILE_NAME));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
