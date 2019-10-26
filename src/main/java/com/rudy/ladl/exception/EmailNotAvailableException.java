package com.rudy.ladl.exception;

public class EmailNotAvailableException extends Exception {
    public EmailNotAvailableException() {
        super("Cet email n'est pas disponible");
    }
    public EmailNotAvailableException(String message) {
        super(message);
    }
}
