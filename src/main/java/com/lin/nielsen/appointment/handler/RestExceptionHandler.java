package com.lin.nielsen.appointment.handler;

import com.lin.nielsen.appointment.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {NotWritablePropertyException.class})
    protected ResponseEntity handleNotWritableProperty(NotWritablePropertyException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity handleNotFoundException(NotFoundException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

}
