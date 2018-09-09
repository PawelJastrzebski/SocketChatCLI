package com.jastrzab.chat.model.requisetUtil;

import java.util.HashMap;
import java.util.Map;

public class DTO {
    Map<String, String> parameters = new HashMap<>();


    public DTO(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public DTO() {
    }

    public void add(String key , String value) {
        this.parameters.put(key,value);
    }
    public String get(String key){
        return this.parameters.get(key);
    }


    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return DTOUtil.serialize(this);
    }
}
