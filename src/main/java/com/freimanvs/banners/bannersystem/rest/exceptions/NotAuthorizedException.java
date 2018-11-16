package com.freimanvs.banners.bannersystem.rest.exceptions;

public class NotAuthorizedException  extends RuntimeException {

    public NotAuthorizedException(String msg) {
        super(msg);
    }

    public NotAuthorizedException(String msg, Throwable t) {
        super(msg, t);
    }

}