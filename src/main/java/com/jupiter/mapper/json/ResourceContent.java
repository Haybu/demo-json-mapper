package com.jupiter.mapper.json;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hxm3459 on 9/26/15.
 */
@Component
@Slf4j
public class ResourceContent {

    @Autowired
    ApplicationContext context;

    public String getContent(String path) {
        StringBuffer content = new StringBuffer();
        Resource resource = context.getResource(path);
        try {
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
