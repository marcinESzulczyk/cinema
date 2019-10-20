package com.example.cinema.exception;

import org.springframework.transaction.annotation.Transactional;

public class BusinessException extends RuntimeException {

    public BusinessException(){}

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String messege, Throwable cause){
        super(messege, cause);
    }

    public BusinessException(Throwable cause){
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
