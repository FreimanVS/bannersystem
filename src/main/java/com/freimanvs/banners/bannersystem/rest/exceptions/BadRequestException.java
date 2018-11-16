package com.freimanvs.banners.bannersystem.rest.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        this("BadRequestException");
    }

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(String msg, Throwable t) {
        super(msg, t);
    }

}