package myth;

import com.myth.zver.interfaces.IEnumMethodKeyGetter;

/**
 * @author imythu
 * @date 2019/8/12 9:20
 */
public enum IntConstantEnum implements IEnumMethodKeyGetter<Integer> {
    /**
     * 测试用
     */
    ENUM_A(1, "A"),
    ENUM_B(2, "B"),
    ENUM_C(3, "C"),
    ENUM_D(4, "D"),
    ENUM_E(5, "E");

    private int key;
    private String value;

    IntConstantEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getMethodKey() {
        return key;
    }
}
