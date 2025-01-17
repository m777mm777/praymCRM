package com.example.exceptions;

public class ConflictServerError extends RuntimeException {
    public ConflictServerError(String message) {
        super(message);
    }
}
