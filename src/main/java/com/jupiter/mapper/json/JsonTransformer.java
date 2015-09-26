package com.jupiter.mapper.json;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hxm3459 on 9/22/15.
 */
@Component
@Slf4j
public class JsonTransformer {

    private static final Log logger = LogFactory.getLog(JsonTransformer.class);

    @Autowired
    ResourceContent resourceContent;

    @Value("${json.mapping.filepath}")
    String mappingFilePath;


    public MappingConfig getMappingConfig() {

        MappingConfig mappingConfig = null;

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mappingConfig = mapper.readValue(jsonContent(), MappingConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mappingConfig;
    }

    private String jsonContent() {
        return resourceContent.getContent(mappingFilePath);
    }

    /**
     * Transforms a JSON segment to another mapped json
     *
     * @param jsonSegment
     * @return
     */
    public String transform(String jsonSegment) throws IOException {

        if (jsonSegment==null) {
            return null;
        }

        Map<String,Object> map = new ObjectMapper().readValue(jsonSegment, HashMap.class);

        MappingConfig mappingConfig = getMappingConfig();
        Map<String,Object> transformedMap = mappingConfig.mapFields(map);

        String json = null;
        if (transformedMap != null) {
            Gson gson = new Gson();
            json = gson.toJson(transformedMap);
            log.info("Transformed JSON: %s", json);
        }

        return json;
    }

}
