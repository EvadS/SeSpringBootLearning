package com.example.filedemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@ComponentScan
//@Configuration
public class StaticResourceConfiguration //extends WebMvcConfigurerAdapter
 {

    public static String uploadDirectory= System.getProperty("user.home") + "/media/";

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/pngFiles/**")
//                .addResourceLocations("file:ext-resources/")
//                .setCachePeriod(0);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//
//        registry
//               .addResourceHandler("/pngFiles/**")
//                //.addResourceHandler("/img/qrcode/**")
//                .addResourceLocations("file:"+uploadDirectory+"/")
//                .setCachePeriod(0);
//    }
}
