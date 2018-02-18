package project.webshop.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.webshop.model.entity.Category;
import project.webshop.repository.CategoryRepository;

import java.util.Iterator;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    @Qualifier("categoryRepository")
    private CategoryRepository categoryRepository;

    // test get category

    @Test
    public void test_get_categories() throws Exception{
        Category category = new Category();
        category.setName("Category1");
        Assert.assertNotNull(category.getName());
    }

    @Test
    public void test_get_the_last_category() throws Exception{
        Category category1 = new Category();
        category1.setName("CategoryTest");
        categoryRepository.save(category1);
        // assertTrue
        Iterable<Category> categories = categoryRepository.findAll();
        Category category2  = new Category();
        if(categories != null){
            // get the last category
            category2 = getLastElement(categories);

        }
        Assert.assertTrue(category1.getName().equals(category2));

    }


    // get last element of Iterator
    public static <T> T getLastElement(final Iterable<T> elements) {
        final Iterator<T> itr = elements.iterator();
        T lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement=itr.next();
        }
        return lastElement;
    }



}
