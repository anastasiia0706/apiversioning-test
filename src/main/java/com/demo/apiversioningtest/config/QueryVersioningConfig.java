package com.demo.apiversioningtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class QueryVersioningConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//          NOTE: Uncomment below to test query versioning
//          configurer.useQueryParam("version");
//          configurer.addSupportedVersions("v1", "v2", "v1.1");
//          configurer.setVersionRequired(true);
    }
}