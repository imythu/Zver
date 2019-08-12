package myth;

import com.myth.zver.interfaces.IEnumMethodKeyGetter;

/**
 * @author imythu
 * @date 2019/8/12 9:27
 */
public enum StringConstantEnum implements IEnumMethodKeyGetter<String> {
    /**
     * 测试用
     */
    ENUM_A("1", "A"),
    ENUM_B("2", "B"),
    ENUM_C("3", "C"),
    ENUM_D("4", "D"),
    ENUM_E("5", "E");

    private String key;
    private String value;

    StringConstantEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getMethodKey() {
        return key;
    }
}
