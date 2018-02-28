package com.cucule.app.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public abstract class ClientBaseController {

    protected boolean hasValidation(BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessageList = new ArrayList<>();
            List<FieldError> fieldErrors = new ArrayList<>();

            fieldErrors.addAll(bindingResult.getFieldErrors());
            fieldErrors.sort((a, b) -> a.getField().compareTo(b.getField()) + a.getCode().compareTo(b.getCode()));

            String beforeField = "";
            for (FieldError fieldError : fieldErrors) {
                if (beforeField.equals(fieldError.getField())) {
                    continue;
                }
                beforeField = fieldError.getField();
                errorMessageList.add(fieldError.getDefaultMessage());
            }
            model.addAttribute("errorMessageList", errorMessageList);
            return true;
        }
        return false;
    }
}
