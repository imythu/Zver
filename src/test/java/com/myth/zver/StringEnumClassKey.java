package com.myth.zver;

import com.myth.zver.interfaces.IEnumMethodKeyGetter;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/11 1:39
 */
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
