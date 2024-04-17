package com.timmy.health.constant;

public enum MailConstant {
    SEND_MAIL_SUCCESS("發送郵件成功!"),
    SEND_MAIL_FAIL("發送郵件失敗!");
    MailConstant(String message) {
        this.message = message;
    }
    private final String message;
}
