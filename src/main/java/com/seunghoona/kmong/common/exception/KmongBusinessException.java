package com.seunghoona.kmong.common.exception;

public class KmongBusinessException extends RuntimeException {

    private static final String MESSAGE_BAD_REQUEST = "요청중 에러가 발생했습니다.";

    public KmongBusinessException() {
        super(MESSAGE_BAD_REQUEST);
    }

    public KmongBusinessException(String message) {
        super(message);
    }
}
