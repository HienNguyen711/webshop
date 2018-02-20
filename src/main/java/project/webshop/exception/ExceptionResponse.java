package project.webshop.exception;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// Exception Response
@Data
@JsonIgnoreProperties
public class ExceptionResponse {
    // Code - Message
    private String Code;
    private String Message;

    public ExceptionResponse(){

    }
    public ExceptionResponse(String _code,String _mesg){
        this.Code= _code;
        this.Message = _mesg;
    }

}
