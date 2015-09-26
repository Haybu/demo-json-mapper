package com.jupiter.mapper.json;

import lombok.Data;

/**
 * Created by hxm3459 on 9/23/15.
 */
@Data
public class MappingData {
    private String column;
    private String value;
    private String type;

    public MappingData() {
        this(null, null, null);
    }

    public MappingData(String column, String value) {
        this(column, value, null);
    }

    public MappingData(String column, String value, String type) {
        setColumn(column);
        setValue(value);
        setType(type);
    }

}
