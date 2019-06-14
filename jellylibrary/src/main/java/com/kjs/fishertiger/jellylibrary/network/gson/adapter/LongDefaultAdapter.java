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
 * 作者：柯嘉少 on 2018/10/19 16:48
 * 邮箱：2449926649@qq.com
 * 说明：
 **/
public class LongDefaultAdapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try{
            String str=jsonElement.getAsString();
            if(str.equals("")||str.equals(" ")||str.equals("null")){
                return 0L;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            return jsonElement.getAsLong();
        }catch (Exception e){
            return 0L;
        }
    }

    @Override
    public JsonElement serialize(Long aLong, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(aLong);
    }
}
