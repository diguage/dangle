package com.diguage.dangle.studies.foundation;

/**
 * 变量的继承性
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-27 14:38
 */
public interface A {
    int x = 7;
}

class B {
    int x = 1;
}

class C extends B implements A {
    public void pX() {
//        System.out.println(x); // 请问:这里的x输出是什么?
    }

    public static void main(String[] args) {
        new C().pX();
    }
}
