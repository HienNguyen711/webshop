package project.webshop.exception.handler;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.hadoop.yarn.webapp.BadRequestException;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import project.webshop.exception.ExceptionResponse;

/**
 * @ExceptionResponse
 * @ControllerAdvice
 */


@ControllerAdvice
public class ExceptionControllerAdvice{// from Exception Response


    // not found
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("Not Found");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    // invalid input
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("Validation Error");
        BindingResult br = ex.getBindingResult();
        String mesg = br.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
        response.setMessage(mesg);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler() {
        ExceptionResponse error = new ExceptionResponse("CofeeShopServicesApi","Invalid attribute name or invalid attribute case");
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.PARTIAL_CONTENT);
    }

    // handle exception
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        return e.getMessage();
    }

    // bad request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequest(BadRequestException e, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("Validation Error");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);

    }



    // http headers
    private HttpHeaders jsonHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }


}
