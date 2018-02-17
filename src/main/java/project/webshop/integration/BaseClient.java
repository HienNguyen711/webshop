package project.webshop.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public abstract class BaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    protected RestTemplate template;
    protected String baseUrl;

    // get Http entity
    protected <T> HttpEntity<T> getHttpEntity(T dto) {
        // headers
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // set content type
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // return
        return new HttpEntity<>(dto, httpHeaders);
    }

}
