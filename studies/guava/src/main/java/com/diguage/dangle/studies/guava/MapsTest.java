package com.diguage.dangle.studies.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by diguage on 16/6/17.
 */
public class MapsTest {
    @Test
    public void testArrayListMultiMap() {
        ArrayListMultimap<String, String> multiMap =
                ArrayListMultimap.create();
        multiMap.put("Foo", "1");
        multiMap.put("Foo", "2");
        multiMap.put("Foo", "3");
        List<String> expected = Lists.newArrayList("1", "2", "3");
        assertEquals(multiMap.get("Foo"), expected);
    }

    @Test
    public void testArrayListMultimapSameKeyValue() {
        ArrayListMultimap<String, String> multiMap =
                ArrayListMultimap.create();
        multiMap.put("Bar", "1");
        multiMap.put("Bar", "2");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        List<String> expected = Lists.
                newArrayList("1", "2", "3", "3", "3");
        assertEquals(multiMap.get("Bar"), expected);
    }

    @Test
    public void testBiMap() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        //This call causes an IllegalArgumentException to be thrown!
//        biMap.put("2", "Tom");
        System.out.println(biMap);
        BiMap<String, String> inverseMap = biMap.inverse();
        inverseMap.put("Cat", "3");
        System.out.println(inverseMap);
        System.out.println(biMap.get("3"));
    }

    @Test
    public void testBiMapForcePut() throws Exception {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.forcePut("2", "Tom");
        assertThat(biMap.containsKey("1"), is(false));
        assertThat(biMap.containsKey("2"), is(true));
        // Guava
    }

    @Test
    public void testBiMapInverse() throws Exception {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.put("2", "Harry");
        assertThat(biMap.get("1"), is("Tom"));
        assertThat(biMap.get("2"), is("Harry"));
        BiMap<String, String> inverseMap = biMap.inverse();
        assertThat(inverseMap.get("Tom"), is("1"));
        assertThat(inverseMap.get("Harry"), is("2"));
    }

    @Test
    public void test() {
        Multimap<Integer, String> map = new
                ImmutableListMultimap.Builder<Integer, String>()
                .put(1, "Foo")
                .putAll(2, "Foo", "Bar", "Baz")
                .putAll(4, "Huey", "Duey", "Luey")
                .put(3, "Single").build();
        System.out.println(map);
    }

    @Test
    public  void main1() {
        String url = "123";
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("userName", "diguage");
        paramMap.put("webSite", "http://www.diguage.com/");
        url = url + "?" + Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
        System.out.println(url);
    }

    @Test
    public void test1() {
        Splitter splitter = Splitter.on('|').trimResults().omitEmptyStrings();
//Next call returns a new instance, does not
// modify the original!
        String sequence = "  1|2  |3|||";
        Iterable<String> parts = splitter.split(sequence);
        System.out.println(parts);
        Splitter sp2 = splitter.trimResults();
//Result would still contain empty elements
        System.out.println(splitter.split(sequence));
        Iterable<String> parts2 = sp2.split(sequence);
        System.out.println(parts2);
        Splitter sp3 = sp2.omitEmptyStrings();
        System.out.println(sp3.split(sequence));

        ArrayList<String> list = Lists.newArrayList(parts);
    }
}
