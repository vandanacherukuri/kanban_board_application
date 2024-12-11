package com.pm_app.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

abstract class AbstractController {

    protected final Logger logger;


    public AbstractController(Class<?> clazz) {

        this.logger = LoggerFactory.getLogger(clazz);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHTMLExceptions(HttpClientErrorException exception) {
        logger.error(exception.getCause().toString() + exception.getCause().toString());
        return ResponseEntity.status(exception.getStatusCode())
                .body(exception.getMessage());
    }

    protected String getTime() {
        Date date = new Date();
        return date.toString();
    }
}
