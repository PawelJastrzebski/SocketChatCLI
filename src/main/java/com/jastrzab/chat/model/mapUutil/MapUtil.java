package com.jastrzab.chat.model.mapUutil;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static final String KEY_VALUE_SEPARATOR = ":#:";
    public static final String ENTIRE_SEPARATOR = ":%:";

    public static Map<String, String> unsterilizeMap(String map){
        Map<String,String> resultMap = new HashMap<>();


        if (map.equals("null") || map.length()==0 ){
            return resultMap;
        }


        String[] entrySet = map.split(ENTIRE_SEPARATOR);

        if(entrySet.length > 0){
            for (String entry : entrySet) {
                String[] data = entry.split(KEY_VALUE_SEPARATOR);
                resultMap.put(data[0],data[1]);
            }
        }else if(  map.contains(KEY_VALUE_SEPARATOR)){
            String[] data = map.split(KEY_VALUE_SEPARATOR);
            resultMap.put(data[0],data[1]);
        }


        return  resultMap;
    }
    public static String serializeMap( Map<String,String> map){

        StringBuilder sb = new StringBuilder();
        map.forEach((key,value) ->{
            sb.append(key);
            sb.append(KEY_VALUE_SEPARATOR);
            sb.append(value);
            sb.append(ENTIRE_SEPARATOR);
        });

        if(sb.length() > 0){
            sb.delete(sb.length() - 3, sb.length());
        }

        return sb.toString();
    }
}
