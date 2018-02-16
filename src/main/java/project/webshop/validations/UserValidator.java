package project.webshop.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import project.webshop.model.entity.user.User;


/**
 * User
 * SecurityService
 */
@Component
public class UserValidator implements Validator{
    @Autowired protected MessageSource messageSource;
//    @Autowired protected SecurityService securityService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return User.class.isAssignableFrom(clazz);
    }

    // validator
    @Override
    public void validate(Object target, Errors errors)
    {
//        // get the user and find user
//        User user = (User) target;
//        String email = user.getEmail();
//        User userByEmail = securityService.findUserByEmail(email);
//        if(userByEmail != null){
//            errors.rejectValue("email", "error.exists", new Object[]{email}, "Email "+email+" already in use");
//        }
    }

}
