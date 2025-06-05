package com.userrisktransactions.Exception;

public class CustomUnauthorizedException extends RuntimeException{
    CustomUnauthorizedException(String message)
    {
        super(message);
    }
}
