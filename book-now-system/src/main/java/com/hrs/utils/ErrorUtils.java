package com.hrs.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

@Component
public class ErrorUtils {

    public void loadErrorList(BindingResult bindingResult, Map<String, String> errorList) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorList.put(error.getField(), error.getDefaultMessage());
            }
        }
    }

    public void addError(Map<String, String> errorList, String key, String value){
        errorList.put(key, value);
    }

}
