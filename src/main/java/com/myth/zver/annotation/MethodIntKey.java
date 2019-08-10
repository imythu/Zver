package com.myth.zver.annotation;

import java.lang.annotation.*;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/10 20:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodIntKey {
    /**
     * 方法编号。
     * @return methodNo
     */
    int methodKey() default Integer.MAX_VALUE;
}
