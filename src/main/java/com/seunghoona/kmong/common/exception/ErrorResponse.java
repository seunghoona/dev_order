package com.seunghoona.kmong.common.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private String message;
    private List<String> errorMessage = new ArrayList<>();

    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }

    public static ErrorResponse of(String message, List<String> errorMessage) {
        return new ErrorResponse(message, errorMessage);
    }

    private ErrorResponse(String message) {
        this.message = message;
    }

    private ErrorResponse(String message, List<String> errorMessage) {
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
