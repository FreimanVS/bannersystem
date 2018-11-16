package com.freimanvs.banners.bannersystem.rest.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("NotFoundException");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}