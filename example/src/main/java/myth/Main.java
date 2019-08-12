package myth;

import com.myth.zver.MethodKeyType;
import com.myth.zver.Zver;
import com.myth.zver.annotation.MethodIntKey;
import com.myth.zver.util.LogUtil;

import java.util.Random;

/**
 * @author imythu
 * @date 2019/8/12 9:29
 */
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

        // 使用 Zver
        try {
            Zver zver = new Zver(Main.class, ConstantGeneral.class, MethodKeyType.INTEGER);
            LogUtil.openInfoPrint();
            zver.invoke(intValue, intValue, stringValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @MethodIntKey(methodKey = ConstantGeneral.A)
    public void a(int value, String string) {
        System.out.println("I'm A. int value is" + value + ". " + " calling " + string);
    }

    @MethodIntKey(methodKey = ConstantGeneral.B)
    private void b(int value) {
        System.out.println("I'm B. " + "calling " + value);
    }

    @MethodIntKey(methodKey = ConstantGeneral.C)
    protected void c() {
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
}
