package project.webshop.dao;


import org.springframework.stereotype.Service;
import project.webshop.model.entity.Category;

import java.util.List;

@Service

public interface CategoryDao extends GenericDao<Category> {
    List<Category> getRootCategories();

    List<Category> getSubcategories(Long parentId);

}
