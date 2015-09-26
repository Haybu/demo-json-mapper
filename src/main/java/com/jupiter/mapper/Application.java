package com.jupiter.mapper;

import com.jupiter.mapper.json.JsonTransformer;
import com.jupiter.mapper.json.MappingConfig;
import com.jupiter.mapper.json.ResourceContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    JsonTransformer transformer;

    @Autowired
    ResourceContent resourceContent;

    @Value("${json.content.filepath}")
    String contentFilePath;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private String jsonContent() {
        return resourceContent.getContent(contentFilePath);
    }

    @Override
    public void run(String... strings) throws Exception {
        String jsonSegment = this.jsonContent();
        String transformedJson = transformer.transform(jsonSegment);
        log.info(transformedJson);
    }
}
