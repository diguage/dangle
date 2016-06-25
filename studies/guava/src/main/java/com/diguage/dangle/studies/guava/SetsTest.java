package com.diguage.dangle.studies.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import static org.assertj.core.api.Assert.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by diguage on 16/6/17.
 */
public class SetsTest {
    @Test
    public void testDifference() {
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("2","3","4");

        ArrayList<Object> abc = Lists.newArrayList();

        for (String s : s1) {

        }

        for (Object s : abc) {

        }

        for (int i = 0; i < s1.size(); i++) {

        }


        System.out.println(Sets.difference(s1,s2));
        System.out.println(Sets.difference(s2,s1));
    }
    @Test
    public void testSymmetricDifference() {
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("2","3","4");
        Sets.SetView setView = Sets.symmetricDifference(s1,s2);
        System.out.println(setView);
    }

    @Test
    public void testIntersection() {
        Set<String> s1 = Sets.newHashSet("1", "2", "3");
        Set<String> s2 = Sets.newHashSet("3", "2", "4");
        Sets.SetView<String> sv = Sets.intersection(s1, s2);
        System.out.println(sv);
//        assertT(sv.size()==2 && sv.contains("2") &&
//                sv.contains("3"));
    }

    @Test
    public void testUnion(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("3","2","4");
        Sets.SetView<String> sv = Sets.union(s1,s2);
        System.out.println(sv);
        // 为什么叫SetView?
    }
}
