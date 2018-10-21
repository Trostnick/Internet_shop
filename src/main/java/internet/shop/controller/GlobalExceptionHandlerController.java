package internet.shop.controller;


import internet.shop.exception.FindByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= {FindByIdException.class})
    public ResponseEntity<String> handleNotFound(RuntimeException ex, WebRequest request){

        return new ResponseEntity<> (ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
