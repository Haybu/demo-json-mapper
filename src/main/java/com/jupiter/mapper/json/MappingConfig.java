package com.jupiter.mapper.json;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hxm3459 on 9/23/15.
 */
@Slf4j
public class MappingConfig {

    private List<MappingData> mapping;

    public List<MappingData> getMapping() {
        return mapping;
    }

    public void setMapping(List<MappingData> mapping) {
        this.mapping = mapping;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer("mapping: \n");
        for (MappingData mdata: mapping) {
            buf.append("column: " + mdata.getColumn() + " ");
            buf.append("value: " + mdata.getValue() + " ");
            buf.append("type: " + mdata.getType() + " ");
            buf.append(("\n"));
        }
        return buf.toString();
    }

    public Map<String, Object> mapFields(Map<String, Object> toBeMapped) {

        if(toBeMapped == null || toBeMapped.keySet() == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        MappingData mappingData;

        for(String key: toBeMapped.keySet()) {
            mappingData = this.getMappingDataForColumn(key);
            if(mappingData != null) {
                result.put(mappingData.getColumn(), castVaue(toBeMapped.get(key), mappingData.getType()));
            } else {
                result.put(key, toBeMapped.get(key)); // put as is
            }
        }

        return result;
    }

    private MappingData getMappingDataForColumn(String column) {
        for (MappingData mdata: mapping) {
            if(column.equalsIgnoreCase(mdata.getValue())) {
                log.info("Mapping is found for column: " + column);
                return mdata;
            }
        }

        return null;
    }

    private Object castVaue(Object value, String type) {
        // TODO: cast
        return value;
    }

}
