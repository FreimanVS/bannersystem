package com.freimanvs.banners.bannersystem.rest.exceptions;

public class NotAcceptableException extends RuntimeException {

    public NotAcceptableException(String msg) {
        super(msg);
    }

    public NotAcceptableException(String msg, Throwable t) {
        super(msg, t);
    }

}