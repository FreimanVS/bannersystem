package com.freimanvs.banners.bannersystem.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException exception,
                                                    WebRequest request) {
        HttpStatus status;
        if (exception instanceof NotFoundException)
            status = HttpStatus.NOT_FOUND;
        else if (exception instanceof BadRequestException)
            status = HttpStatus.BAD_REQUEST;
        else if (exception instanceof ForbiddenException)
            status = HttpStatus.FORBIDDEN;
        else if (exception instanceof NotAllowedException)
            status = HttpStatus.METHOD_NOT_ALLOWED;
        else if (exception instanceof NotAcceptableException)
            status = HttpStatus.NOT_ACCEPTABLE;
        else if (exception instanceof NotAuthorizedException)
            status = HttpStatus.UNAUTHORIZED;
        else
            status = HttpStatus.SEE_OTHER;

        return ResponseEntity.status(status)
                .contentType(MediaType.TEXT_PLAIN)
                .body(exception.getLocalizedMessage());
    }
}