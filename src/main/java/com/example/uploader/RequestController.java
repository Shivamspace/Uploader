package com.example.uploader;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class RequestController {
    public static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/uploader")
    public ResponseEntity uploader(@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + "src/main/resources/" + fileName);

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
        String path = System.getProperty("user.dir") + "/" + "src/main/resources/" + fileName;

        File file = new File(path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));


        ResponseEntity<Object> responseEntity = ResponseEntity.ok().contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
}
