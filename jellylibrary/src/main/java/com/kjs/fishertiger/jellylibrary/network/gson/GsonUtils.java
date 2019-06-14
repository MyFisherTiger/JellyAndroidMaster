package com.kjs.fishertiger.jellylibrary.network.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kjs.fishertiger.jellylibrary.config.Config;
import com.kjs.fishertiger.jellylibrary.network.gson.adapter.DoubleDefaultAdapter;
import com.kjs.fishertiger.jellylibrary.network.gson.adapter.FloatDefaultAdapter;
import com.kjs.fishertiger.jellylibrary.network.gson.adapter.IntegerDefaultAdapter;
import com.kjs.fishertiger.jellylibrary.network.gson.adapter.LongDefaultAdapter;

import java.lang.reflect.Type;
import java.util.List;


public class GsonUtils {
    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
            .registerTypeAdapter(int.class, new IntegerDefaultAdapter())

            .registerTypeAdapter(Long.class, new LongDefaultAdapter())
            .registerTypeAdapter(long.class, new LongDefaultAdapter())

            .registerTypeAdapter(Float.class, new FloatDefaultAdapter())
            .registerTypeAdapter(float.class, new FloatDefaultAdapter())

            .registerTypeAdapter(Double.class, new DoubleDefaultAdapter())
            .registerTypeAdapter(double.class, new DoubleDefaultAdapter())

            .create();


    /**
     * 转化为object
     *
     * @param reader        json数据
     * @param tranformClass 要转化的Class
     * @param <T>
     * @return
     */
    public static <T> T fromJsonObject(String reader, Class tranformClass) throws IllegalStateException {
        T result;
        Type type = new ParameterizedTypeImpl(Config.MClASS, new Class[]{tranformClass});
        try {
            result = (T) gson.fromJson(reader, type);
        } catch (Exception e) {
            type = new ParameterizedTypeImpl(Config.MClASS, new Class[]{String.class});
            //如果有错尝试字符解析String.class
            try {
                result = (T) gson.fromJson(reader, type);
            }catch (Exception el){
                throw new IllegalStateException("出错的解析类：" + tranformClass.getSimpleName()+ "------>>" + e.getMessage());
            }

        }
        return result;
    }

    /**
     * 转化为列表
     *
     * @param reader    json数据
     * @param listClass 要转化的Class
     * @param <T>
     * @return
     */
    public static <T> T fromJsonArray(String reader, Class listClass) throws IllegalStateException {
        T result;
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{listClass});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(Config.MClASS, new Type[]{listType});
        try {
            result = (T) gson.fromJson(reader, type);
        } catch (Exception e) {
            type = new ParameterizedTypeImpl(Config.MClASS, new Class[]{String.class});
            //如果有错尝试字符解析String.class
            try {
                result = (T) gson.fromJson(reader, type);
            }catch (Exception el){
                throw new IllegalStateException("出错的解析类：" + listClass.getSimpleName()+ "------>>" + e.getMessage());
            }
        }
        return result;
    }


    public static <T> T fromJsonNoCommonClass(String reader, Class listClass) {
        T result;
        try {
            result = (T) gson.fromJson(reader, listClass);
        } catch (Exception e) {
            throw new IllegalStateException("出错的解析类：" + listClass.getSimpleName() + "------>>" + e.getMessage());
        }
        return result;
    }
}
