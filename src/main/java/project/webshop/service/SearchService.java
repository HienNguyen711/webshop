package project.webshop.service;


import org.springframework.stereotype.Service;
import project.webshop.model.dto.ProductDto;

import java.util.List;
import java.util.Map;

@Service
public interface SearchService {
    // find product by filter
    List<ProductDto> findProductsByFilters(Map<String, String> filters, int offset, int limit) throws Exception;
}

