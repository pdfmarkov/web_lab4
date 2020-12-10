package com.itmo.lab4.backend.validators;

import com.itmo.lab4.backend.controllers.request.AuthenticationRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Service
public class AuthenticationRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return AuthenticationRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        AuthenticationRequest request = (AuthenticationRequest) obj;
        Pattern loginPattern = Pattern.compile("^[a-z][a-z\\d]*$", Pattern.CASE_INSENSITIVE);
        if(!Pattern.matches(loginPattern.toString(), request.getUsername())){
            errors.rejectValue("username", "notmatches",
                    "A login can consist of latin letters and numbers, but cannot start with a number");
            return;
        }
        Pattern passwordPattern = Pattern.compile("^[a-z\\d]*$", Pattern.CASE_INSENSITIVE);
        if(!Pattern.matches(passwordPattern.toString(), request.getPassword())){
            errors.rejectValue("password", "notmatches",
                    "Password can only consist of letters of the Latin alphabet and numbers.");
        }
    }
}
