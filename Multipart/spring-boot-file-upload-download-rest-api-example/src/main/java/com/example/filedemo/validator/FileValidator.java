package com.example.filedemo.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * вариант для mvc - нам не подходит
 * https://www.jackrutorial.com/2018/05/spring-mvc-multipart-file-upload-example.html
 */
@Component
public class FileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        MultipartFile fileUpload = (MultipartFile) target;

     //   CommonsMultipartFile[] commonsMultipartFiles = fileUpload;

      //  for(CommonsMultipartFile multipartFile : commonsMultipartFiles) {
            if(fileUpload.getSize() <= 100) {
                errors.rejectValue("files", "missing.file");
            }
      //  }
    }

}