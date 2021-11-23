package com.example.uploader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final String UPLOAD_DIR = System.getProperty("user.dir") + "/" + "src/main/resources/uploads/";
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/uploader")
    public ResponseEntity uploader(@RequestParam("fileName") String fileName, @NotNull @RequestParam("file") MultipartFile file) throws IOException {

        logger.debug("Request=>>" + fileName + file);
        File convertFile = new File(UPLOAD_DIR + fileName);

        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile)) {
            fout.write(file.getBytes());
        } catch (MaxUploadSizeExceededException exe) {
            throw new MaxUploadSizeExceededException(123333, exe);
        }
        logger.debug("Response=>" + new ResponseEntity(HttpStatus.CREATED));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/downloader")
    public ResponseEntity downloader(@RequestParam String fileName) throws FileNotFoundException {
        String path = UPLOAD_DIR + fileName;

        File file = new File(path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));


        return ResponseEntity.ok().contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).<Object>body(resource);
    }

    @GetMapping("/listDocs")
    public ResponseEntity<List<Documents>> listDocuments() throws IOException {

        File f = new File(UPLOAD_DIR);

        File[] pathnames = f.listFiles();
        List<Documents> docs = new ArrayList<>();
        Documents d = null;
        for (File pathname : pathnames) {
            d = new Documents();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(pathname));
            d.setId(UUID.randomUUID());
            d.setName(pathname.getName());
            d.setType(Files.probeContentType(new File(pathname.toString()).toPath()));
            d.setTimestamp(pathname.lastModified());
            docs.add(d);
        }

        return new ResponseEntity<List<Documents>>(docs, HttpStatus.OK);
    }
}
