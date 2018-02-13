package project.webshop.service;

import org.springframework.stereotype.Service;
import project.webshop.model.dto.CategoryDto;

import java.util.List;

// Category service
@Service
public interface CategoryService {
    // add category : Category DTO
    CategoryDto addCategory(CategoryDto categoryDto) throws Exception;

    // edit category
    CategoryDto editCategory(CategoryDto categoryDto) throws Exception;

    // delete category by id
    void deleteCategory(Long id) throws Exception;

    CategoryDto setParentToChild(Long childId, Long parentId) throws Exception;

    void deleteParentFromChild(Long childId) throws Exception;

    List<CategoryDto> getRootCategories() throws Exception;

    List<CategoryDto> getSubcategories(Long categoryId) throws Exception;
}
