package com.example.uploader;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
@CrossOrigin("*")
public class RequestController {
    public static final String UPLOAD_DIR = System.getProperty("user.dir") + "/" + "src/main/resources/";

    @PostMapping("/uploader")
    public ResponseEntity uploader(@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File(UPLOAD_DIR + fileName);

        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile)) {
            fout.write(file.getBytes());
        } catch (Exception exe) {
            exe.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/downloader")
    public ResponseEntity downloader(@RequestParam String fileName) throws FileNotFoundException {
        String path = UPLOAD_DIR + fileName;

        File file = new File(path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));


        ResponseEntity<Object> responseEntity = ResponseEntity.ok().contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
    @GetMapping("/listDocs")
    public ResponseEntity<List<Documents>>listDocuments() throws IOException {

        File f = new File(UPLOAD_DIR);

        File[] pathnames = f.listFiles();
List<Documents> docs= new ArrayList<>();
//public Documents(int id, String name, String type, String timestamp) {

        for (File pathname : pathnames) {
            Documents d = new Documents();
             d.setId(UUID.randomUUID());
            d.setName(pathname.getName());
            d.setType(Files.probeContentType(new File(pathname.toString()).toPath()));
            d.setTimestamp(pathname.lastModified());
            docs.add(d);
            System.out.println(docs);
        }

        return new ResponseEntity<List<Documents>>(docs,HttpStatus.OK);
    }
}
