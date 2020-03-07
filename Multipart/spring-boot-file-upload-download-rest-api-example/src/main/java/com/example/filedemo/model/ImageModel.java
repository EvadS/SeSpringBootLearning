package com.example.filedemo.model;

import com.example.filedemo.validator.ValidImage;
import org.springframework.web.multipart.MultipartFile;

public class ImageModel {
    private String imageName;

    @ValidImage
    private MultipartFile[] files;


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public ImageModel() {
    }
}
