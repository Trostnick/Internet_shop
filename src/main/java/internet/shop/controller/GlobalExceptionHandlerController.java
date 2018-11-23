package internet.shop.controller;


import internet.shop.exception.FileUploadException;
import internet.shop.exception.ValidationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<String> handleNotFound(RuntimeException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {ValidationException.class, FileUploadException.class})
    public ResponseEntity<Map> handleValidationException(ValidationException ex, WebRequest request) {
        return new ResponseEntity<> (ex.getMessageMap(), HttpStatus.BAD_REQUEST);
    }
}
