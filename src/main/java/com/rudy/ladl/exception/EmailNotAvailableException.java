package com.rudy.ladl.exception;

import com.rudy.ladl.util.Constant;

public class EmailNotAvailableException extends Exception {
    public EmailNotAvailableException() {
        super(Constant.ERROR_MSG_EMAIL_NOT_AVAILABLE);
    }
    public EmailNotAvailableException(String message) {
        super(message);
    }
}
