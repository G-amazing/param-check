package com.gamazing.exception;

public class PublicException extends CommonException {
    public PublicException() {super();}
    public PublicException(String status, String msg) {super(status, msg);}
    public PublicException(String message, String status, String msg) {
        super(message, status, msg);
    }
}
