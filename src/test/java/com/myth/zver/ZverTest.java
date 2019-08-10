package com.myth.zver;


import org.junit.Test;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/10 21:21
 */
public class ZverTest {

    public static final int test = 1;

    @Test
    public void testInit() throws Exception {
        System.out.println("测试 int 普通类");
        Zver intGeneralClassZver = new Zver(MethodCodeing.class, GeneralClassKey.class, MethodKeyType.INTEGER);
        // 打印 execute method 0
        System.out.println(intGeneralClassZver.invoke(0));
        // 打印 execute method 1
        System.out.println(intGeneralClassZver.invoke(1));
        // 打印 execute method 2
        intGeneralClassZver.invoke(2);
        // 打印 execute method 3
        intGeneralClassZver.invoke(3);

        System.out.println("\n\n测试 int 枚举类");
        Zver intEnumClassZver = new Zver(MethodCodeing.class, IntEnumClassKey.class, MethodKeyType.INTEGER);
        // 打印 execute method 0
        System.out.println(intEnumClassZver.invoke(0));
        // 打印 execute method 1
        System.out.println(intEnumClassZver.invoke(1));

        System.out.println("\n\n测试 string 普通类");
        Zver stringGeneralClassZver = new Zver(MethodCodeing.class, GeneralClassKey.class, MethodKeyType.STRING);
        // 打印 execute method 4
        stringGeneralClassZver.invoke("KEY4");
        // 打印 null
        System.out.println(stringGeneralClassZver.invoke("not exits key"));

        System.out.println("\n\n测试 string 枚举类");
        Zver stringEnumClassZver = new Zver(MethodCodeing.class, StringEnumClassKey.class, MethodKeyType.STRING);
        // 打印 execute method 4
        stringEnumClassZver.invoke("KEY4");
        // 打印 null
        System.out.println(stringEnumClassZver.invoke("not exits key"));
    }

}