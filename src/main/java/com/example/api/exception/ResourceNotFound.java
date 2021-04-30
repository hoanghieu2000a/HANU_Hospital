package com.example.api.exception;

public class ResourceNotFound extends RuntimeException {

    @Override
    public String getMessage() {
        return "Resource Not Found";
    }
}
