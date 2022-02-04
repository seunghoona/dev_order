package com.seunghoona.kmong.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class KmongRestAdvice {

    @ExceptionHandler(value = { DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> dataIntegrityViolationException(Exception e) {
        log.error("dataIntegrityViolationException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(Message.MESSAGE_ALREADY_REGISTER_MEMBER.getMessage()), CONFLICT);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class, NoResultDataException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(Exception e) {
        log.error("entityNotFoundException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {KmongBusinessException.class})
    public ResponseEntity<ErrorResponse> kmongBusinessException(Exception e) {
        log.error("kmongBusinessException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, ArithmeticException.class})
    public ResponseEntity<ErrorResponse> standardException(Exception e) {
        log.error("businessException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {AuthorizationException.class})
    public ResponseEntity<ErrorResponse> tokenException(Exception e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), UNAUTHORIZED);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), INTERNAL_SERVER_ERROR);
    }

}
