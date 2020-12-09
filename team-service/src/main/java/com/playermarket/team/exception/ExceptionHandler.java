package com.playermarket.team.exception;

import com.playermarket.team.model.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("unchecked")
public class ExceptionHandler {
    private static final String GENERAL_MESSAGE = "Generic exception";

    public static <R> ResponseEntity<R> getException(Exception e) {
        ApplicationError applicationError = new ApplicationError();

        if (e.getMessage() != null) {
            applicationError.setMessage(e.getMessage());
        } else {
            applicationError.setMessage(GENERAL_MESSAGE);
        }

        return new ResponseEntity<R>((R) applicationError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
