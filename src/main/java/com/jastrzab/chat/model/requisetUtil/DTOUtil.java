package com.jastrzab.chat.model.requisetUtil;

import com.jastrzab.chat.model.mapUutil.MapUtil;

import java.util.Map;

public class DTOUtil {

    public static DTO parse(String serializeRequest){
        return  new DTO(MapUtil.unsterilizeMap(serializeRequest));
    }

    public static String serialize(DTO DTO){
        Map<String, String> parameters = DTO.getParameters();
        return MapUtil.serializeMap( parameters);
    }

}
