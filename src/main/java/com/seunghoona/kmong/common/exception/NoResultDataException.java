package com.seunghoona.kmong.common.exception;

import javax.persistence.EntityNotFoundException;

import static com.seunghoona.kmong.common.exception.Message.MESSAGE_NO_RESULT_DATA;


public class NoResultDataException extends EntityNotFoundException {

    public NoResultDataException() {
        super(MESSAGE_NO_RESULT_DATA.getMessage());
    }

    public NoResultDataException(String message) {
        super(message);
    }
}
