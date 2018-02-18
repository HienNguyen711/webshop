package project.webshop.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception  {

    private static final long serialVersionUID = -781206437165935086L;

    private static String makeExceptionMessage(Errors errors) {
        String msg = null;
        if(errors.hasFieldErrors()) {
            List<FieldError> fieldErrors = errors.getFieldErrors();
            StringBuilder buf = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                buf.append('`')
                    .append(fieldError.getField())
                    .append("`: ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
            }

            msg = buf.toString();
        }

        return msg;
    }

    private static String makeDebugMessage(Errors errors) {
        String msg = null;
        if(errors.hasFieldErrors()) {
            List<FieldError> fieldErrors = errors.getFieldErrors();
            msg = fieldErrors.stream().map(FieldError::toString).collect(Collectors.joining("; "));
        }

        return msg;
    }

    public ValidationException(Errors errors) {
        this(makeExceptionMessage(errors));
//        setDebugMessage(makeDebugMessage(errors));
    }

    public ValidationException(String string) {
        super(string);
    }

}
