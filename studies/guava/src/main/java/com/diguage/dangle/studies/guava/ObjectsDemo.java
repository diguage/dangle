package com.diguage.dangle.studies.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by diguage on 16/6/2.
 */
public class ObjectsDemo {
    @Test
    public void listnull() {
        List<String> list = null;

        for (String str : MoreObjects.firstNonNull(list, Collections.<String> emptyList())) {
            System.out.println(str);
        }

        list = new ArrayList<String>();
        list.add("http://stackoverflow.com/");

        for (String str : MoreObjects.firstNonNull(list, Collections.<String> emptyList())) {
            System.out.println(str);
        }
    }

    public void test() {
//        Predicate<StringBuilder> predicate = null;
//    /* predicate returning whether the builder is empty */
//                List<StringBuilder> builders = Lists.newArrayList();
//        List<StringBuilder> view = Lists.filter(builders, predicate);
//
//        for (int i = 0; i < 10000; i++) {
//            builders.add(new StringBuilder());
//        }
//        builders.get(8000).append("bar");
//
//        StringBuilder firstNonEmpty = view.get(0);
    }

    public static <T> List<T> filter(Iterable<T> userLists, Predicate<T> predicate) {
        return Lists.newArrayList(Iterables.filter(userLists, predicate));
    }


}
