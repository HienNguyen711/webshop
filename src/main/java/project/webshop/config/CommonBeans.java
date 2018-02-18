package project.webshop.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
//import pl.training.backend.common.model.Mapper;
@ImportResource ("classpath:mail.xml")

@Configuration
public class CommonBeans {

    private static final String ENCODING = "utf-8";
//
//    @Bean
//    public Mapper mapper(MessageSource messageSource) {
//        return new Mapper(messageSource);
//    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(ENCODING);
        messageSource.setBasename("messages");
        return messageSource;
    }

}
