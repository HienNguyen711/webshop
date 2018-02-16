package project.webshop.validations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.webshop.model.dto.ProductDto;
import project.webshop.model.entity.Product;
import project.webshop.service.ProductService;
import project.webshop.service.impl.ProductServiceImpl;


@Component
public class ProductFormValidator implements Validator
{
    // test with admin : userId = 1
    @Autowired protected MessageSource messageSource;

    @Autowired protected ProductServiceImpl productService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
//    {
//        ProductDto productDto = (ProductDto) target;
//
//        Product p = productService.getProduct(((ProductDto) target).getId(),600851475143L);//productId and userId
//
//        if(p != null){
////            errors.rejectValue("sku", "error.exists", new Object[]{sku}, "Product SKU "+sku+" already exists");
//        }
    }


}
