package com.example.uploader.exception;

public class FileExceededException extends RuntimeException {
    public FileExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
