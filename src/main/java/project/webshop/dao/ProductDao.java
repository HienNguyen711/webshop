package project.webshop.dao;

import org.springframework.stereotype.Service;
import project.webshop.model.entity.Product;

import java.util.List;
import java.util.Map;

@Service
public interface ProductDao extends GenericDao<Product> {

    List<Product> getProducts(int offset, int limit);

    List<Product> getProductsByFilters(Map<String, String> filters, int offset, int limit);
}
