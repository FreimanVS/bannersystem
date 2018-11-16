package com.freimanvs.banners.bannersystem.rest.exceptions;

public class NotAllowedException extends RuntimeException {

    public NotAllowedException(String msg) {
        super(msg);
    }

    public NotAllowedException(String msg, Throwable t) {
        super(msg, t);
    }

}