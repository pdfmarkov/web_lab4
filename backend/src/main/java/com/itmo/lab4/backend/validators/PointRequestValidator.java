package com.itmo.lab4.backend.validators;

import com.itmo.lab4.backend.controllers.request.PointRequest;
import com.itmo.lab4.backend.database.entities.PointEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpServletResponse;

@Service
public class PointRequestValidator implements Validator {

    private static final String BAD_FORMAT_EXCEPTION_MESSAGE = "X, Y coordinates and radius must be given by a number";
    double R_MAX = 5.0, R_MIN = -5.0;
    private static final String NOT_IN_INTERVAL_EXCEPTION_MESSAGE = "X, Y coordinates and radius must be in the specified interval";
    private static final String TOO_LONG_EXCEPTION_MESSAGE = "X coordinates and radius must be no more than 10 characters";

    @Override
    public boolean supports(Class<?> aClass) {
        return PointRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PointRequest point = (PointRequest) obj;
        if(point.getX() == null || point.getY() == null || point.getRadius() == null){
            errors.reject("null", BAD_FORMAT_EXCEPTION_MESSAGE);
            return;
        }

        Double x, y, radius;

        try{
            x = Double.parseDouble(point.getX());
            y = Double.parseDouble(point.getY());
            radius = Double.parseDouble(point.getRadius());
        } catch (NumberFormatException ex){

            errors.reject("format", BAD_FORMAT_EXCEPTION_MESSAGE);
            return;
        }

        if(point.getX().length() > 10 || point.getY().length() > 10 ||
                point.getRadius().length() > 10){
            errors.reject("too long", TOO_LONG_EXCEPTION_MESSAGE);
            return;
        }

        if(radius > R_MAX || radius < R_MIN){
            errors.reject("notininterval", NOT_IN_INTERVAL_EXCEPTION_MESSAGE);
        }
    }
}
