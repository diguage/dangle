package com.diguage.dangle.studies.guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by diguage on 15/11/9.
 */
public class JoinerDemo {
    @Test
    public void testJoiner() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        String sql = String.format("select * from sku where id in (%s)", Joiner.on(",").skipNulls().join(idList));
        System.out.println(sql);
    }
}
