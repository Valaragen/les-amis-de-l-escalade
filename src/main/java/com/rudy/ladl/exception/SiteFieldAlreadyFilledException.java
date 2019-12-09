package com.rudy.ladl.exception;

import com.rudy.ladl.util.Constant;

public class SiteFieldAlreadyFilledException extends Exception {
    public SiteFieldAlreadyFilledException() {
        super(Constant.ERROR_MSG_SITE_FIELD_ALREADY_FILLED);
    }
    public SiteFieldAlreadyFilledException(String message) {
        super(message);
    }
}
