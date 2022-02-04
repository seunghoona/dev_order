package com.seunghoona.kmong.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class KmongRestAdvice {

    @ExceptionHandler(value = { DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> dataIntegrityViolationException(Exception e) {
        log.error("dataIntegrityViolationException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(Message.MESSAGE_ALREADY_REGISTER_MEMBER.getMessage()), CONFLICT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> methodArgumentValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> list = convertErrorMessage(bindingResult);
        return new ResponseEntity<>(ErrorResponse.of(Message.MESSAGE_BAD_REQUEST.getMessage(), list), BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class, NoResultDataException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(Exception e) {
        log.error("entityNotFoundException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {KmongBusinessException.class})
    public ResponseEntity<ErrorResponse> kmongBusinessException(KmongBusinessException e) {
        log.error("kmongBusinessException : {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.of(e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, ArithmeticException.class})
    public ResponseEntity<ErrorResponse> standardException(Exception e) {
        log.error("kmongBusinessException : {}", e.getMessage());
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

    private List<String> convertErrorMessage(BindingResult bindingResult) {
        List<String> list = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("](은)는 ");
            sb.append(fieldError.getDefaultMessage());
            sb.append(" 입력된 값: [");
            sb.append(fieldError.getRejectedValue());
            sb.append("]");
            list.add(sb.toString());
        }
        return list;
    }

}
