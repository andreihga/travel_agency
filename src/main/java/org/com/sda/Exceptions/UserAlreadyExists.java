package org.com.sda.Exceptions;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String message){
        super(message);
    }
}
