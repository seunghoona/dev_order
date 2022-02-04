package com.seunghoona.kmong.common.exception;

public enum Message {
    MESSAGE_NO_RESULT_DATA ("데이터가 존재하지 않습니다."),
    MESSAGE_NO_RESULT_PRODUCT("존재하지 않는 상품입니다."),
    MESSAGE_ALREADY_REGISTER_MEMBER ("이미 가입된 계정입니다."),
    MESSAGE_BAD_REQUEST("요청중 오류가 발생하였습니다.")
    ;

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String format(Message message, Object... arg) {
        return String.format(message.getMessage(), arg);
    }


}
