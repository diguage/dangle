package com.diguage.dangle.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author D瓜哥(http://www.diguage.com/)
 * @since 2016/4/26.
 */
public class Lists {
    public static <T> List<T> union(List<T> tList, List<T>... tListArray) {
        int length = tList.size();
        for (List<T> ts : tListArray) {
            length += ts.size();
        }
        List<T> result = new ArrayList<T>(length);
        result.addAll(tList);
        for (List<T> ts : tListArray) {
            result.addAll(ts);
        }
        return result;
    }

    public static <T> List<T> ofList(Collection<T> data) {
        List<T> result = new ArrayList<T>(data.size());
        result.addAll(data);
        return result;
    }
}
