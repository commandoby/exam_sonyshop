package com.commandoby.sonyShop.exceptions;

public class ServiceException extends ControllerException {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
