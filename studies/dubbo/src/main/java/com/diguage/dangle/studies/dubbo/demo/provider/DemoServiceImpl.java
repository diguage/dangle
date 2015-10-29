package com.diguage.dangle.studies.dubbo.demo.provider;

import com.diguage.dangle.studies.dubbo.demo.DemoService;

/**
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-22 18:42
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
