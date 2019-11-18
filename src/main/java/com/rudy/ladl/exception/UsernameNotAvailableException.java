package com.rudy.ladl.exception;

import com.rudy.ladl.util.Constant;

public class UsernameNotAvailableException extends Exception {
    public UsernameNotAvailableException() {
        super(Constant.ERROR_MSG_USERNAME_NOT_AVAILABLE);
    }
    public UsernameNotAvailableException(String message) {
        super(message);
    }
}
