package internet.shop.controller.exception;


import internet.shop.exception.NonUniqueFieldException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<String> handleNotFound(RuntimeException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {IllegalArgumentException.class, NonUniqueFieldException.class})
    public ResponseEntity<String> handleUnprocessableEntity(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
