package com.rudy.ladl.exception;

public class UsernameNotAvailableException extends Exception {
    public UsernameNotAvailableException() {
        super("Ce pseudo n'est pas disponible");
    }
    public UsernameNotAvailableException(String message) {
        super(message);
    }
}
