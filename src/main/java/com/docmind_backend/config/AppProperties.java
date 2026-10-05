package com.docmind_backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AppProperties
 */

@Configuration 
@ConfigurationProperties(prefix = "app")
@Getter 
@Setter 
public class AppProperties {

    private RegProperties regProperties = new RegProperties();
    private CoreProperties coreProperties = new CoreProperties();

    @Getter 
    @Setter 
    @AllArgsConstructor 
    @NoArgsConstructor 
    public static class RegProperties {
        private int chunkSize;
        private int chunkoverlap;
        private int topK=5;
        private double similarityThreshold=0.0;

    }

    @Getter 
    @Setter 
    @AllArgsConstructor 
    @NoArgsConstructor 
    public static class CoreProperties {
       private String allowedOrigins = "*";
       private String allowedMethods="GET, POST, PUT, DELETE, OPTIONS";
       private String allowedHeaders="*";

    }

}
