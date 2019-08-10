package com.myth.zver.interfaces;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/11 1:05
 */
public interface IEnumMethodKeyGetter <T> {
    String KEY_NO_GETTER_METHOD_NAME = "getMethodKey";
    /**
     * 枚举类获取 keyNo 的需要实现的方法
     * @return keyNo
     */
    T getMethodKey();
}
