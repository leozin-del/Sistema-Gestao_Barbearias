package com.barbearia.BARBEARIAPRO.exception;

public class EmailAlreadyInUseException extends IllegalArgumentException{

    public EmailAlreadyInUseException(String message) {
        super(message);
    }

}
