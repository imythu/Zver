package com.myth.zver;

import com.myth.zver.interfaces.IEnumMethodKeyGetter;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/11 0:20
 */
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
