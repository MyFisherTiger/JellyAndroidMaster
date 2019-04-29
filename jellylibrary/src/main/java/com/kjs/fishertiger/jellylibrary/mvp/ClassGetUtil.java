package com.kjs.fishertiger.jellylibrary.mvp;

import java.lang.reflect.ParameterizedType;

/**
 * 获取类实例
 */

public class ClassGetUtil {

    public static <T> T getClass(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (ClassCastException e) {
        }
        return null;
    }
}
