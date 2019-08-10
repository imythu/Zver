package com.myth.zver;


import com.myth.zver.annotation.MethodIntKey;
import com.myth.zver.annotation.MethodStringKey;
import com.myth.zver.interfaces.IEnumMethodKeyGetter;
import com.myth.zver.util.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.myth.zver.MethodKeyType.INTEGER;

/**
 * description 初始化将要执行的一系列方法，并提供执行指定方法的接口。
 * authorEmail imythu@qq.com
 * @author myth
 * @date 2019/8/10 20:55
 */
public class Zver {

    private static final LogUtil logger = new LogUtil();

    private Map<Integer, Method> intKeyMethodMap;
    private Map<String, Method> stringMethodMap;
    private Object targetObj;

    /**
     * 初始化 Zver。
     * @param methodsClass 编写的 if-else 或者 switch-case 块 对应方法的类
     * @param keyClass 存储 if-else 或者 switch-case 块 对应方法的 key 的类，可以是普通类或者枚举类
     * @throws Exception 使用反射时的异常
     */
    public Zver(Class<?> methodsClass, Class<?> keyClass, int keyType) throws Exception {
        this.targetObj = methodsClass.newInstance();
        if (keyType == INTEGER) {
            logger.serr("key type is int.");
            this.intKeyMethodMap = new HashMap<>(50);
            Set<Integer> methodKeys = scanIntMethodKey(keyClass);
            Method[] methods = methodsClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MethodIntKey.class)) {
                    int key = method.getAnnotation(MethodIntKey.class).methodKey();
                    if (methodKeys.contains(key)) {
                        intKeyMethodMap.put(key, method);
                    }
                }
            }
            logger.serr("intKeyMethodMap :" + intKeyMethodMap);
        } else {
            logger.serr("key type is string.");
            stringMethodMap = new HashMap<>(50);
            Set<String> methodKeys = scanStringMethodKey(keyClass);
            Method[] methods = methodsClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MethodStringKey.class)) {
                    String key = method.getAnnotation(MethodStringKey.class).methodKey();
                    if (methodKeys.contains(key)) {
                        stringMethodMap.put(key, method);
                    }
                }
            }

            logger.serr("stringMethodMap" + stringMethodMap);
        }
    }
    /**
     * 扫描 string 类型 method key。
     * 只记录 static final 类型的字段。
     * @param keyClass 存储 if-else 或者 switch-case 块 对应方法的 key 的类，可以是普通类或者枚举类
     * @return 指定类中所有的 key
     */
    private Set<String> scanStringMethodKey(Class<?> keyClass) {
        Field[] fields = keyClass.getDeclaredFields();
        Set<String> keys = new HashSet<>(fields.length);

        // 定义 keys 的类是枚举类 Enum
        if (IEnumMethodKeyGetter.class.isAssignableFrom(keyClass)) {
            logger.serr("key class is enum class.");
            Object[] keyClassInstances = keyClass.getEnumConstants();
            if (keyClassInstances == null) {
                return keys;
            }
            for (Object keyClassInstance : keyClassInstances) {
                try {
                    keys.add((String) keyClass.getDeclaredMethod(IEnumMethodKeyGetter.KEY_NO_GETTER_METHOD_NAME).invoke(keyClassInstance));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    logger.serr(e.toString());
                }
            }
        }

        logger.serr("key class is general class.");
        // 定义 keys 的类是普通类
        Object keyClassInstance = null;
        try {
            keyClassInstance = keyClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.serr(e.toString());
        }
        if (keyClassInstance == null) {
            return keys;
        }
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            // 只记录 static final 类型的字段
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    keys.add((String) field.get(keyClassInstance));
                } catch (Exception e) {
                    logger.serr("field " + field.getName() + ", get key failed.");
                    logger.serr(e.toString());
                }
            }
        }
        return keys;
    }

    /**
     * 扫描 int 类型 method key。
     * 只记录 static final 类型的字段。
     * @param keyClass 存储 if-else 或者 switch-case 块 对应方法的 key 的类，可以是普通类或者枚举类
     * @return 指定类中所有的 key
     */
    private Set<Integer> scanIntMethodKey(Class<?> keyClass) {
        Field[] fields = keyClass.getDeclaredFields();
        Set<Integer> keys = new HashSet<>(fields.length);
        // 定义 keys 的类是枚举类 Enum
        if (IEnumMethodKeyGetter.class.isAssignableFrom(keyClass)) {
            logger.serr("key class is enum class.");
            Object[] keyClassInstances = keyClass.getEnumConstants();
            if (keyClassInstances == null) {
                return keys;
            }
            for (Object keyClassInstance : keyClassInstances) {
                // 枚举类的枚举变量都是 public static final 的不需要检测
                try {
                    keys.add((Integer) keyClass.getDeclaredMethod(IEnumMethodKeyGetter.KEY_NO_GETTER_METHOD_NAME).invoke(keyClassInstance));
                } catch (Exception e) {
                    logger.serr(e.toString());
                }
            }
            return keys;
        }

        logger.serr("key class is general class.");
        // 定义 keys 的类是普通类
        Object keyClassInstance = null;
        try {
            keyClassInstance = keyClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.serr(e.toString());
        }
        if (keyClassInstance == null) {
            return keys;
        }
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            // 只记录 static final 类型的字段
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    keys.add( field.getInt(keyClassInstance));
                } catch (Exception e) {
                    logger.serr("field " + field.getName() + ", get key failed.");
                    logger.serr(e.toString());
                }
            }
        }
        return keys;
    }

    /**
     * 调用指定 key 的方法。
     * 如果指定 key 的方法不存在，则返回 null。
     * @param methodKey 方法 key
     * @param args 方法参数
     * @return 方法返回值
     * @throws Exception 使用反射调用方法时肯出现的异常
     */
    public Object invoke(Integer methodKey, Object... args) throws Exception {
        Method method = intKeyMethodMap.get(methodKey);
        if (method == null) {
            logger.serr("cannot find method for key " + methodKey);
            return null;
        }
        return method.invoke(targetObj, args);
    }

    public Object invoke(String methodKey, Object... args) throws Exception {
        Method method = stringMethodMap.get(methodKey);
        if (method == null) {
            logger.serr("cannot find method for key " + methodKey);
            return null;
        }
        return method.invoke(targetObj, args);
    }
}
