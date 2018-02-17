package project.webshop.integration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import project.webshop.model.dto.CategoryDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CategoryApiClient extends BaseClient{

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryApiClient.class);

    public CategoryApiClient(RestTemplate template, String baseUrl) {
        this.template = template;
        this.baseUrl = baseUrl;
    }

//    public List<CategoryDto> getCategories() {
//        try {
//            ParameterizedTypeReference<List<RouteDto>> type = new ParameterizedTypeReference<List<RouteDto>>() {};
//            ResponseEntity<List<RouteDto>> instance = template.exchange(baseUrl, HttpMethod.GET, null, type);
//            LOGGER.info("Got response from Routes API "+instance.getStatusCode());
//            if(instance.getStatusCode()== HttpStatus.OK)
//                return instance.getBody();
//            else return Collections.emptyList();
//        } catch (Exception e) {
//            LOGGER.error("Unable to get routes from Routes API", e);
//            return Collections.emptyList();
//        }
//    }
}
