package com.kjs.fishertiger.jellylibrary.db.helper;

public interface  SharePreferenceHelper {
    void saveByNameAndKeyWithSP(String name, String key, Object object);
    void saveByKeyWithSP(String key, Object object);
    <T> T queryByNameAndKeyWithSP(String name, String key, Class<T> clazz, Object defaultValue);
    <T> T queryByKeyWithSP(String key, Class<T> clazz, Object defaultValue);
}
