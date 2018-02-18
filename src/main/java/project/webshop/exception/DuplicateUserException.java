package project.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateUserException extends RuntimeException{

    private static final long serialVersionUID = -6391206997652962051L;

    public DuplicateUserException(String string) {
        super(string);
    }
}
