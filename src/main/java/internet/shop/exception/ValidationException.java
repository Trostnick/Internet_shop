package internet.shop.exception;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidationException extends RuntimeException {
    private HashMap<String, List<String>> validationMessageMap;

    public ValidationException() {
        this.validationMessageMap = new HashMap<>();
    }

    public void add(String propertyName, String message) {
        if (validationMessageMap.containsKey(propertyName)) {
            List<String> curPropertyMessageList = validationMessageMap.get(propertyName);
            curPropertyMessageList.add(message);
        } else {
            List<String> newMessageList = new ArrayList<>();
            newMessageList.add(message);
            validationMessageMap.put(propertyName, newMessageList);
        }
    }

    public void add(BindingResult bindingResult) {
        List<ObjectError> validationErrorList = bindingResult.getAllErrors();
        if (!(validationErrorList.isEmpty())) {
            validationErrorList.forEach(error -> {
                this.add(((FieldError) error).getField(), error.getDefaultMessage());
            });
        }
    }

    public void throwIf() {
        if (!(validationMessageMap.isEmpty())) {
            throw this;
        }
    }


    public HashMap<String, List<String>> getMessageMap() {
        return this.validationMessageMap;
    }

}
