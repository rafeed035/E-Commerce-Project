package com.rafeed.ecommerceproject.CustomException.Exceptions;

public class EntityNotfoundException extends Exception{
    public EntityNotfoundException() {
        super();
    }

    public EntityNotfoundException(String message) {
        super(message);
    }

    public EntityNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotfoundException(Throwable cause) {
        super(cause);
    }

    protected EntityNotfoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
