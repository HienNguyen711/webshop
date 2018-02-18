//package project.webshop.validations;
//
//
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import project.webshop.model.entity.user.User;
//
//import javax.validation.Validator;
//
//public class UserLoginValidator implements Validator {
//    private static final String FIELD_REQUIRED_CODE = "field.required";
//    private static final String FIELD_REQUIRED_MSG = " Field required";
//
////    private StoredApplicationUserProvider storedApplicationUserProvider;
//
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return User.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", FIELD_REQUIRED_CODE, FIELD_REQUIRED_MSG);
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", FIELD_REQUIRED_CODE, FIELD_REQUIRED_MSG);
//
//        User user = (User) target;
//
//        String email = user.getEmail();
////        User storedUser = storedApplicationUserProvider.storedApplicationUserForEmail(email);
////        if(null == storedUser) {
////            throw new ResourceNotFoundException("Email address not found: " + email);
////        } else {
////
////            if(!storedUser.getEmail().equals(email)) {
////                errors.rejectValue("email", "email.mismatch", "Email address mismatch.");
////            }
////
////            String rawPassword = user.getPassword();
////            BCryptPasswordEncoder encoder = storedApplicationUserProvider.getBCryptPasswordEncoder();
////            if(!encoder.matches(rawPassword, storedUser.getPassword())) {
////                errors.rejectValue("password", "password.mismatch", "Password mismatch.");
////            }
////        }
//    }
//    }
//
//}
