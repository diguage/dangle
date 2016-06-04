package com.diguage.dangle.studies.guava;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by diguage on 16/6/2.
 */
public class IterableDemo {
    @Test
    public void getFirstElement() {
        String firstLetter = FluentIterable.of(new String[]{"A", "B", "C", "D", "E"})
                .first().or("DEFAULT");
        assertThat(firstLetter).isEqualTo("A");
    }

    @Test
    public void geTDefaultFromEmptyIterable() {
        String firstLetter = FluentIterable.of(new String[]{})
                .first().or("DEFAULT");
        assertThat(firstLetter).isEqualTo("DEFAULT");
    }

    @Test
    public void getFirstValueFromIterables() {
        List<String> letters = Lists.newArrayList("A", "B", "C", "D", "E");
        String firstLetter = Iterables.getFirst(letters, "DEFAULT");
        assertThat(firstLetter).isEqualTo("A");
    }

    @Test
    public void getDefaultValueFromIterables() {
        String firstLetter = Iterables.getFirst(Collections.<String>emptyList(), "DEFAULT");
        assertThat(firstLetter).isEqualTo("DEFAULT");
    }
}
