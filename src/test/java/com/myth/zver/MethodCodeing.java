package com.myth.zver;

import com.myth.zver.annotation.MethodIntKey;
import com.myth.zver.annotation.MethodStringKey;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/11 0:38
 */
public class MethodCodeing {

    @MethodIntKey(methodKey = 0)
    public String test0() {
        return "execute method 0";
    }

    @MethodIntKey(methodKey = 1)
    public String test1() {
        return "execute method 1";
    }

    @MethodIntKey(methodKey = 2)
    public void test2() {
        System.out.println("execute method 2");
    }

    @MethodIntKey(methodKey = 3)
    public void test3() {
        System.out.println("execute method 3");
    }

    @MethodStringKey(methodKey = "KEY4")
    public void test4() {
        System.out.println("execute method 4");
    }
}
