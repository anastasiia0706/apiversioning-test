package com.demo.apiversioningtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathVersioningConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        // configurer.usePathSegment(1); // <- Note: uncomment to test path versioning

        // Note: Is not supported yet by IDEA
        // configurer.supportedVersions("v1", "v2", "v1.1");
        // configurer.defaultVersion("v2");
        // configurer.versionRequired(false);
    }
}