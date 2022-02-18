package com.example.uploader.controller;

import com.example.uploader.model.Documents;
import com.example.uploader.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class RequestController {
     @Autowired
     DocumentService documentService;
     
     @PostMapping("/uploader")
     public ResponseEntity<Object> uploader(@RequestParam("fileName") String fileName , @NotNull @RequestParam("file") MultipartFile file) throws IOException {
          
          documentService.uploadDocument ( fileName , file );
          return new ResponseEntity<> ( HttpStatus.CREATED );
     }
     
     @GetMapping("/downloader")
     public ResponseEntity downloader(@RequestParam String fileName) throws IOException {
          InputStreamResource resource = documentService.downloadDocument ( fileName );
          return ResponseEntity.ok ( ).contentLength ( resource.contentLength ( ) )
                  .contentType ( MediaType.parseMediaType ( "application/txt" ) ).<Object>body ( resource );
     }
     
     @GetMapping("/listDocs")
     public ResponseEntity<List<Documents>> listDocuments() throws IOException {
          List<Documents> docs = documentService.listDocs ( );
          return new ResponseEntity<> ( docs , HttpStatus.OK );
     }
}
