package com.cinema.exception;


public class NoSessionException extends Exception {
    public NoSessionException(String message){
        super(message);
    }
    public NoSessionException(){
        super();
    }
}
