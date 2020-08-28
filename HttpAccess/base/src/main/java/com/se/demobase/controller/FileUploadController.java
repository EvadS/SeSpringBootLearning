package com.se.demobase.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private Log log = LogFactory.getLog(FileUploadController.class);



    @RequestMapping(method = RequestMethod.POST)
    public String handleFileUpload(
            @RequestParam("user-file") MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        System.out.println("File name: "+name);
        //todo save to a file via multipartFile.getInputStream()
        byte[] bytes = multipartFile.getBytes();
        System.out.println("File uploaded content:\n" + new String(bytes));
        return "file uploaded";
    }


    @ApiOperation(value = "upload file base .", nickname = "upload",
            notes = "upload file with additional param", tags = {})

    @PostMapping("/upload-param")
      public ResponseEntity uploadFile(@RequestParam("customerId") int customerId,
                                       @RequestPart("file") MultipartFile multipartFile) {
        log.info("customerId: " + customerId);
        log.info("Received multipart file - original filename: " + multipartFile.getOriginalFilename());
        log.info("content-type: " + multipartFile.getContentType());
        log.info("size: " + multipartFile.getSize());

        return  ResponseEntity.ok(multipartFile.getOriginalFilename());
    }
}
