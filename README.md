# 代码优化库 Zver
## 先说说这个 Zver 是干嘛的：两行代码代替几十上百(无上限)的 if-else 和 switch-case 代码
## [项目Github 地址](https://github.com/imythu/Zver)
## [掘金地址](https://juejin.im/post/5d4eefe9f265da03cf7a7db5)
- 问题引入
    - 首先，我们有如下代码，然后需要根据值来执行不同的逻辑。

    ```
    public class ConstantGeneral {
        // int 类型
        public static final int A = 1;
        public static final int B = 2;
        public static final int C = 3;
        public static final int D = 4;
        public static final int E = 5;

        // String 类型
        public static final String SA = "1";
        public static final String SB = "2";
        public static final String SC = "3";
        public static final String SD = "4";
        public static final String SE = "5";
    }
    ```


    - 先来看看用 if-else 或者 switch-case 来处理的话，代码大概是这样的。可以看到，需要写太多相似的代码了，而且一次次的比较性能肯定也不太好。Zver 就是用来解决这个问题的。
    
    ```
    public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        // 1 - 5 随机数
        int intValue = random.nextInt(5) + 1;
        String stringValue = intValue + "";

        // 测试用，参数
        int param1 = 10086;
        String param2 = "10086";

        // 用 if-else
        if (intValue == ConstantGeneral.A) {
            System.out.println("I'm A. param1 value is " + param1 + " calling " + param2);
        } else if (intValue == ConstantGeneral.B) {
            System.out.println("I'm B. " + "calling " + param2);
        } else if (intValue == ConstantGeneral.C) {
            System.out.println("I'm C. ");
        } else if (intValue == ConstantGeneral.D) {
            System.out.println("I'm D. ");
        } else if (intValue == ConstantGeneral.E) {
            System.out.println("I'm E. ");
        }

        // 用 switch
        switch (intValue) {
            case ConstantGeneral.A:
                System.out.println("I'm A. param1 value is " + param1 + " calling " + param2);
                break;
            case ConstantGeneral.B:
                System.out.println("I'm B. " + "calling " + param2);
                break;
            case ConstantGeneral.C:
                System.out.println("I'm C. ");
                break;
            case ConstantGeneral.D:
                System.out.println("I'm D. ");
                break;
            case ConstantGeneral.E:
                System.out.println("I'm E. ");
                break;
                default:
                    break;
        }
    }
}
    ```

- 使用 Zver 解决。
    - 先来看看使用 Zver 后代码是什么样的。
        - 首先，把每个值要执行的逻辑封装成一个个的方法，这些方法可以放在任意类下，方法修饰符可以是public、private、protect。
        - 封装好的方法如下，这个注解是必须的，后面介绍使用方法的时候再具体解释这个注释，不过这儿看名字大概也能知道一点它的作用。
            ```
            @MethodIntKey(methodKey = ConstantGeneral.A)
            public void a(int value, String string) {
                System.out.println("I'm A. int value is" + value + ". " + " calling " + string);
            }

            @MethodIntKey(methodKey = ConstantGeneral.B)
            private void b(String string) {
                System.out.println("I'm B. " + "calling " + string);
            }

            @MethodIntKey(methodKey = ConstantGeneral.C)
            public void c() {
                System.out.println("I'm C. ");
            }

            @MethodIntKey(methodKey = ConstantGeneral.D)
            public void d() {
                System.out.println("I'm D. ");
            }

            @MethodIntKey(methodKey = ConstantGeneral.E)
            public void e() {
                System.out.println("I'm E. ");
            }
            ```
        
        - 最后，使用 Zver。
            ```
                public static void main(String[] args) {
                    Random random = new Random();
                    // 1 - 5 随机数
                    int intValue = random.nextInt(5) + 1;
                    String stringValue = intValue + "";

                    // 测试用，参数
                    int param1 = 10086;
                    String param2 = "10086";

                    // 使用 Zver
                    try {
                        Zver zver = new Zver(Main.class, ConstantGeneral.class, MethodKeyType.INTEGER);
                        zver.invoke(intValue, intValue, stringValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            ```
        - 对比一下，可以看到，除去 try-catch，只要  ***两行***  代码！！！而且 Zver 内部使用的是哈希表，不需要执行比较操作，性能上要好很多。
        
    - 什么时候使用Zver。
    可以看到使用 if-else 或者 switch-case 的话不但要写很多代码，而且每次都可能要比较很多次。这个时候就是使用 Zver 的时候。

- Zver 具体点的使用方法？
    - 第一，当然是引入依赖了。
        - 下载 jar 包，下载地址[点击下载](https://imyth.top/zver/zver-1.0.0.2-release.jar?1.0.0.2)。
        - maven 还没发布，等哪天发布了，下面的就可以在 maven 中用了。<br/>
            如果实在要用 maven 的话，一种方式是去 GitHub 上把源码下下来本地执行``` mvn clean install -Dmaven.test.skip=true ```安装到本地仓库就可以了。
            ```
            <dependency>
                <groupId>myth</groupId>
                <artifactId>zver</artifactId>
                <version>1.0-release</version>
            </dependency>
            ```
    - 第二，将原来实现在 if-else 或者 switch-case 中的代码封装成一个个的方法。
        - 方法位置。这些方法可以放在任意类中。
        - 修饰符。方法可以是public、private、protected。
        - 参数。方法的参数可以是任意数量的。
            - ***但是请注意：*** 这些方法的前 n 个参数类型必须对齐。
                - 正确例子。这些参数从左到右，依次的类型是相同的。
                    ```
                    @MethodIntKey(methodKey = 1)
                    public void method1() {}
                    
                    @MethodIntKey(methodKey = 2)
                    public void method2(int i, String str) {}
                    
                    @MethodIntKey(methodKey = 3)
                    public String method3(int j) {}
                    
                    @MethodIntKey(methodKey = 4)
                    public int method4(int k, String str, long emmm) {}
                    ```
                - 错误例子。第三个方法的参数从左到右，与其他方法参数依次的类型是不同。这就会造成第三个方法执行失败。对其他方法无影响。
                    ```
                    @MethodIntKey(methodKey = 1)
                    public void method1() {}
                    
                    @MethodIntKey(methodKey = 2)
                    public int method2(int i, String str) {}
                    
                    @MethodIntKey(methodKey = 3)
                    public String method3(String str) {}
                    
                    @MethodIntKey(methodKey = 4)
                    public void method4(int k, String str, long emmm) {}
                    ```
    - 第三，加注解。使用``` @MethodIntKey ``` 或者 ``` @MethodStringKey ``` 注解注明此方法的 methodKey。
        - 关于注解。这两个注解分别用于 methodKey 是 int 和 String 类型的时候。那这个 methodKey 是什么呢？其实就是上面问题引入中的使用的那些常量值。
            - 注解中 methodKey 的值建议像最开始问题引入中的例子那样直接使用常量值，不要使用魔法值。
            ```
            @MethodIntKey(methodKey = ConstantGeneral.A)
            public void method1() {}
                    
            @MethodIntKey(methodKey = ConstantGeneral.B)
            public void method2(int i, String str) {}
                    
            @MethodIntKey(methodKey = ConstantGeneral.C)
            public String method3(int j) {}
                    
            @MethodIntKey(methodKey = ConstantGeneral.D)
            public int method4(int k, String str, long emmm) {}
            ```
            再贴一下这个常量类：
            ```
            public class ConstantGeneral {
                /**
                * int 类型
                */
                public static final int A = 1;
                public static final int B = 2;
                public static final int C = 3;
                public static final int D = 4;
                public static final int E = 5;
            }
            ```
    - 最后。``` MethodKeyType ``` 这个参数有两个值 分别是 ``` MethodKeyType.INTEGER ``` 和 ``` MethodKeyType.STRING ```，这个参数是用来标识 methodKey 是int 还是 String 类型的，传错会出现错误。<br/>
    注意：实例化（new） Zver 实例时，第三个参数只能传递``` MethodKeyType.INTEGER ``` 和 ``` MethodKeyType.STRING ``` 两个中的一个，否则会出错。
    ``` 
    // 第一个参数是包含上面定义的方法的类，第二个参数是常量值类，第三个参数表明 methodKey 类型
    Zver zver = new Zver(Main.class, ConstantGeneral.class, MethodKeyType.INTEGER);
    
    
    // 第一个参数是一个值（即 methodKey），后面的所有参数是可变参数object... args，
    将所有上面定义的所有方法可能用到的参数按照参数最长的方法的参数列表传入进去，
    比如这儿就是按照 method4 的参数列表来传递值得
    zver.invoke(intValue, intValue, stringValue， longValue);
    ```
    
    - 其他：
        - Zver#invoke 方法的返回值是 Object。
            - 在以下情况返回 null。
            - 要执行的方法找不到。
            - 要执行的方法返回类型为 void。
            - 要执行的方法的参数个数大于传入的参数个数。
        - 上面的用使用的 methodKey 类型是 int，如果要使用String 的话就改用 ``` @MethodStringKey ```注解
        - 如果常量类是枚举类，这个枚举类必须实现 ``` IEnumMethodKeyGetter<T> ``` 接口，泛型 ``` T ``` 只能使用 ``` String ``` 和 ``` Integer ```。并实现接口方法 ``` T getMethodKey() ``` 返回一个当前枚举常量的对应的 methodKey。
- 参考：
    - [Zver Source GitHub](https://github.com/imythu/zver)
    - [Zver JavaDoc](https://imyth.top/zver/JavaDoc)

## 最后来个大点的字体，如果觉得还行的话能不能去 [GitHub ](https://github.com/imythu/zver)给个 star ？

![点个赞再走](https://user-gold-cdn.xitu.io/2019/8/12/16c83e241849997b?w=369&h=372&f=gif&s=1731233)