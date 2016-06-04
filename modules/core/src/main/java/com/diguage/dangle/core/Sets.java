package com.diguage.dangle.core;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author D瓜哥(http://www.diguage.com/)
 * @since 2016/4/25.
 */
public class Sets {
    public static <T> Set<T> ofHashSet(T t, T... ta) {
        Set<T> result = new HashSet<T>();
        result.add(t);
        for (T ei : ta) {
            result.add(ei);
        }
        return result;
    }

    public static <T> Set<T> ofHashSet(Collection<T> data) {
        Set<T> result = new HashSet<T>(data.size());
        result.addAll(data);
        return result;
    }

    public static <T> T pickOne(Collection<T> data) {
        if (isEmpty(data)) {
            return null;
        }
        // TODO 如何根据参数类型创建相应的数组?
//        Type mySuperClass = data.getClass().getGenericSuperclass();
//        Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
//        T[] array = (T[]) Array.newInstance((Class) type, data.size());
        T[] array = (T[]) data.toArray();
        return array[0];
    }

    public static <T> boolean isEmpty(Collection<T> data) {
        return data == null || data.isEmpty();
    }

    public static void main(String[] args) {
        Collection<String> data = new ArrayList<String>();
        data.add("D");
        data.add("瓜哥");
        System.out.println(pickOne(data));
    }
}
