package com.destiny.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName JsonUtil
 * @Author Administrator
 * @Date 2019/7/2019:13
 * @Version 1.0
 **/
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    public static <T> T jsonToObject(String json,Class<T> clazz){
        Assert.hasLength(json,() ->"json 不能为空");
        T t = null;
        try {
            t = objectMapper.readValue(json,clazz);
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> jsonListToObject(List<String> jsons, Class<T> clazz){
       Assert.notNull(jsons,"jsons 不能为空");
        List<T> list = Lists.newArrayList();
            jsons.stream().forEach(json -> {
                try{
                    list.add(objectMapper.readValue(json,clazz));
                } catch (Exception e){
                    e.printStackTrace();
                }

            });

        return list;
    }

    public static String objectToJson(Object obj){
        String json = "";
        try{
            json = objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public static List<String> objectListToJson(List<Object> objs){
        List list = Lists.newArrayList();
        objs.stream().forEach(obj ->{
            try{
                list.add(objectMapper.writeValueAsString(obj));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        return list;
    }
}
