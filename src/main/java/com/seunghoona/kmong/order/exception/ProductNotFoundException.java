package com.seunghoona.kmong.order.exception;

import com.seunghoona.kmong.common.exception.Message;
import com.seunghoona.kmong.common.exception.NoResultDataException;

public class ProductNotFoundException extends NoResultDataException {

    public ProductNotFoundException() {
        super(Message.MESSAGE_NO_RESULT_PRODUCT.getMessage());
    }
}
