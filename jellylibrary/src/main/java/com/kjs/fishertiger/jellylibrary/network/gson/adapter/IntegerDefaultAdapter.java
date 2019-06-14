package com.kjs.fishertiger.jellylibrary.network.gson.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * 作者：柯嘉少 on 2018/10/19 15:21
 * 邮箱：2449926649@qq.com
 * 说明：
 **/
public class IntegerDefaultAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try{
            String str=jsonElement.getAsString();
            if(str.equals("")||str.equals(" ")||str.equals("null")){
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            return jsonElement.getAsInt();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public JsonElement serialize(Integer integer, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(integer);
    }
}
