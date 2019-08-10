package com.myth.zver.util;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/11 1:13
 */
public class LogUtil {

    /**
     * 控制日志信息打印，默认不打印信息.
     */
    private static boolean printInfo = false;

    /**
     * 开启日志信息打印
     */
    public static void openInfoPrint() {
        printInfo = true;
    }

    /**
     * 关闭日志信息打印.
     */
    public static void closeInfoPrint() {
        printInfo = false;
    }

    /**
     * 打印信息
     * @param string 要打印的信息
     */
    public void serr(String string) {
        if (printInfo) {
            System.err.println(string);

        }
    }
}
