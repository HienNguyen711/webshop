package project.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.ProductDto;
import project.webshop.service.SearchService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${route.search}")
public class SearchProductsController {
    private static final String SEARCH_VIEW = "webshop/search";


    private static final String URL_BASE_REDIRECT = "redirect:/home";

    private static final int BUTTONS_TO_SHOW = 5;

    private static final int INITIAL_PAGE = 0;

    private static final int INITIAL_PAGE_SIZE = 5;

    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.POST)
    @Cacheable("products")
    public ResponseEntity<?> getSearchedProducts(@RequestBody Map<String, String> filters,
                                                 @RequestParam(value="offset",required =true) int offset,
                                                 @RequestParam("limit") int limit) throws Exception {
        List<ProductDto> productDtos;
        try {
            productDtos = searchService.findProductsByFilters(filters, offset, limit);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
