package com.diguage.dangle.studies.dubbo.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-22 20:17
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"http://10.20.160.198/wiki/display/dubbo/provider.xml"});

        context.start();
        System.in.read(); // 按任意键退出

    }
}
