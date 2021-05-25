package com.example.uploader;

public class FileExceededException extends RuntimeException {
    public FileExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
