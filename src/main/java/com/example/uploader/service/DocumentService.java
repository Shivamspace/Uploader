package com.example.uploader.service;

import com.example.uploader.model.Documents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DocumentService {
     
     @Value("${system.upload.directory}")
     private String uploaddir;
     @Value("${spring.servlet.multipart.max-file-size}")
     private String maxsize;
     private final Logger logger = LoggerFactory.getLogger ( this.getClass ( ) );
     
     public void uploadDocument(String fileName , MultipartFile file) throws IOException {
          System.out.println (uploaddir );
          System.out.println (maxsize );
          logger.info ( "File Upload started at " + LocalDateTime.now ( ) );
          File convertFile = new File ( uploaddir + fileName );
          
          boolean newFile = convertFile.createNewFile ( );
          
          if (newFile) {
               try (FileOutputStream fout = new FileOutputStream ( convertFile )) {
                    fout.write ( file.getBytes ( ) );
                    logger.info ( "File Uploaded  at " + uploaddir );
               } catch (MaxUploadSizeExceededException exe) {
                    logger.info ( "File Uploade failed because "+exe.getLocalizedMessage () );
                    throw new MaxUploadSizeExceededException ( Integer.parseInt ( maxsize ) , exe );
               }
          }
        
     }
     
     public InputStreamResource downloadDocument(String fileName) throws FileNotFoundException {
          logger.info ( "File download started at " + LocalDateTime.now ( ) );
          
          String path = uploaddir + fileName;
          File file = new File ( path );
          return new InputStreamResource ( new FileInputStream ( file ) );
     }
     
     public List<Documents> listDocs() throws IOException {
          File f = new File ( uploaddir );
          File[] pathnames = f.listFiles ( );
          List<Documents> docs = new ArrayList<> ( );
          Documents d = null;
          for (File pathname : pathnames) {
               d = new Documents ( );
               InputStreamResource resource = new InputStreamResource ( new FileInputStream ( pathname ) );
               d.setId ( UUID.randomUUID ( ) );
               d.setName ( pathname.getName ( ) );
               d.setType ( Files.probeContentType ( new File ( pathname.toString ( ) ).toPath ( ) ) );
               d.setTimestamp ( pathname.lastModified ( ) );
               docs.add ( d );
          }
          logger.info ( "total Files: " + docs.size ( ) );
          logger.info ( "Files: " + docs );
          
          return docs;
     }
}
