package com.transfer.exception;

public class ApplicationException extends Exception {

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message) {
        super(message);
    }
}
