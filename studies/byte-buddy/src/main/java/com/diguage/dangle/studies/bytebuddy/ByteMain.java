package com.diguage.dangle.studies.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

/**
 * Created by diguage on 16/8/19.
 */
public class ByteMain {
    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader(),
                        ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        Object instance = dynamicType.newInstance();
        System.out.println(instance.toString());
    }
}
