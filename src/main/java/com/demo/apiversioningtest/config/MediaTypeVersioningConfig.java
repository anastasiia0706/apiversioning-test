package com.demo.apiversioningtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MediaTypeVersioningConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        NOTE: uncomment below to test media type versioning
//        configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON, "version");
//        configurer.setDefaultVersion("v2");
    }
}