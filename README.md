# *Zver*
### *问题：*
- 在写代码的过程中，常常会遇到根据某个变量的值，来执行相应的逻辑，
而当这个值可能的取值特别多的时候就会造成代码量庞大、不太容易阅读、
代码还不太美观、要写大量的逻辑判断代码、***心情变差***等问题。   


### *本工具使用方法：*
1.为你的项目添加依赖

   -  maven 方式，在项目中加入以下依赖
```$xslt
        <dependency>
            <groupId>myth</groupId>
            <artifactId>zver</artifactId>
            <version>1.0-release</version>
        </dependency>
``` 
   - [下载 jar 包]() 并导入到你的项目中
2.现在可以开始使用了。

    - 首先，有一个普通类 GenaralClassKey.java 和 一个枚举类 EnumClassKey.java
```$xslt
public class GenaralClassKey {
    public static final int TESTGenaralClassKey1 = 1;
    public static final int TESTGenaralClassKey2 = 2;
    public static final int TESTGenaralClassKey3 = 3;
}

public enum  EnumClassKey implements IEnumClassMethodKeyGetter {
    TEST_ENUM1(0, "test IEnumClassMethodKeyGetter"),
    TEST_ENUM2(1, "test IEnumClassMethodKeyGetter");

    private int anInt;
    private String string;

    EnumClassKey(int anInt, String string) {
        this.anInt = anInt;
        this.string = string;
    }

    @Override
    public Integer getKeyNo() {
        return anInt;
    }
}

```

