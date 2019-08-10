# 代码优化库Zver
## 两行代码代替几十上百(无上限)的 if-else 和 switch-case 代码
## [项目Github 地址](https://github.com/imythu/Zver)
## [掘金地址](https://juejin.im/post/5d4eefe9f265da03cf7a7db5)
- 使用 switch-case 或者 if-else
    - ![](https://user-gold-cdn.xitu.io/2019/8/11/16c7ccde8f510452?w=598&h=420&f=png&s=316888)
- 使用 Zver：

        Zver zver = new Zver(methodsClass, keyClass, MethodKeyType.INTEGER);
        zver.invoke(messageType.getType());
### *问题：*
- 在写代码的过程中，常常会遇到根据某个变量的值，来执行相应的逻辑，
而当这个值可能的取值特别多的时候就会造成代码量庞大、不太容易阅读、
代码还不太美观、要写大量的逻辑判断代码、***心情变差***等问题。   

### *本工具使用方法：*
1.为你的项目添加依赖。
   -  maven 方式，在项目中加入以下依赖。
```$xslt
        <dependency>
            <groupId>myth</groupId>
            <artifactId>zver</artifactId>
            <version>1.0-release</version>
        </dependency>
``` 
   - 使用 jar 包方式，[下载 jar 包](https://imyth.top/zver-1.0-release.jar) 并导入到你的项目中。
   
2.使用方法介绍。

    1.创建 Zver 实例。有一下两种方式。第一种针对 methodKey 为 int 整数，第二种针对 methodKey 为 String。
    
        Zver zver = new Zver(methodsClass, keyClass, MethodKeyType.INTEGER);
        Zver zver = new Zver(methodsClass, keyClass, MethodKeyType.STRING);
        
    2.传入 methodKey 以及 args 方法参数用方法，args 方法参数为可变参数，非必须。
    
        zver.invoke(1);
        zver.invoke(1, args);
        zver.invoke("1");
        zver.invoke("1", args);
    
    3.是否打印日志信息。使用com.myth.zver.util.LogUtil#openInfoPrint() 和 closeInfoPrint() 来开启或关闭信息打印。    
    
    4.特别注意：如果是枚举类的话，一定要实现 com.myth.zver.interfaces.IEnumClassMethodKeyGetter<T> 接口。此接口泛型只支持 Integer 和 String。
    
    
3.使用例子。
- 测试类。
```
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
```
- 普通类 GenaralClassKey.java 。
- 枚举类 IntEnumClassKey.java、StringEnumClassKey。
        - 特别注意：如果是枚举类的话，一定要实现 com.myth.zver.interfaces.IEnumClassMethodKeyGetter 接口。
```$xslt

public enum EnumClassKey implements IEnumMethodKeyGetter<Integer> {
    TEST_ENUM1(0, "test IEnumClassMethodKeyGetter"),
    TEST_ENUM2(1, "test IEnumClassMethodKeyGetter");

    private int anInt;
    private String string;

    EnumClassKey(int anInt, String string) {
        this.anInt = anInt;
        this.string = string;
    }

    @Override
    public Integer getMethodKey() {
        return anInt;
    }
}
```
```$xslt
public enum IntEnumClassKey implements IEnumMethodKeyGetter<Integer> {
    TEST_ENUM1(0, "test IEnumClassMethodKeyGetter"),
    TEST_ENUM2(1, "test IEnumClassMethodKeyGetter");

    private int anInt;
    private String string;

    IntEnumClassKey(int anInt, String string) {
        this.anInt = anInt;
        this.string = string;
    }

    @Override
    public Integer getMethodKey() {
        return anInt;
    }
}

```
```$xslt
public enum  StringEnumClassKey implements IEnumMethodKeyGetter<String> {

    TEST4("KEY4", "key 4");

    private String key;
    private String value;

    StringEnumClassKey(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getMethodKey() {
        return key;
    }
}
```
打印结果如下
![](https://user-gold-cdn.xitu.io/2019/8/11/16c7cc4bb8d2cb7e?w=791&h=476&f=png&s=422706)