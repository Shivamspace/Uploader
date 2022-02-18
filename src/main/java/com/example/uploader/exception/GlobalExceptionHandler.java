package com.example.uploader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler implements ResponseErrorHandler {
     
     //StandardServletMultipartResolver
     @ExceptionHandler(MultipartException.class)
     public ResponseEntity<String> handleError1(MultipartException e , RedirectAttributes redirectAttributes) {
          
          return new ResponseEntity<String> ( e.getLocalizedMessage ( ) + "Error has Occurred" , HttpStatus.INTERNAL_SERVER_ERROR );
          
     }
     
     //CommonsMultipartResolver
     @ExceptionHandler(MaxUploadSizeExceededException.class)
     public ResponseEntity<DocumentError> handleError2(MaxUploadSizeExceededException e) {
          
          DocumentError documentError = new DocumentError ( e.getLocalizedMessage ( ) , LocalDateTime.now ( ) );
          
          return new ResponseEntity<> ( documentError , HttpStatus.INTERNAL_SERVER_ERROR );
          
     }
     
     @Override
     public boolean hasError(ClientHttpResponse response) throws IOException {
          return false;
     }
     
     @Override
     public void handleError(ClientHttpResponse response) throws IOException {
     
     }
}