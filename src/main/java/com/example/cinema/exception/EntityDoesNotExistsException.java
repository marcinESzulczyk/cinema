package com.example.cinema.exception;

public class EntityDoesNotExistsException extends RuntimeException{

    public EntityDoesNotExistsException(){}

    public EntityDoesNotExistsException(String message){
        super(message);
    }

    public EntityDoesNotExistsException(String messege, Throwable cause){
        super(messege, cause);
    }

    public EntityDoesNotExistsException(Throwable cause){
        super(cause);
    }

    public EntityDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
