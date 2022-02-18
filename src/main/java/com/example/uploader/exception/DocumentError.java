package com.example.uploader.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class DocumentError {
     public DocumentError(String errormsg , LocalDateTime localDateTime) {
          this.errormsg = errormsg;
          this.localDateTime = localDateTime;
     }
     
     @JsonProperty("Error Message")
     private String errormsg;
     @JsonProperty("TimeStamp")
     private LocalDateTime localDateTime;
     
     public String getErrormsg() {
          return errormsg;
     }
     
     public void setErrormsg(String errormsg) {
          this.errormsg = errormsg;
     }
     
     public LocalDateTime getLocalDateTime() {
          return localDateTime;
     }
     
     public void setLocalDateTime(LocalDateTime localDateTime) {
          this.localDateTime = localDateTime;
     }
}
