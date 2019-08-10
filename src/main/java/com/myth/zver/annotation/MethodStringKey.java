package com.myth.zver.annotation;

import java.lang.annotation.*;

/**
 * description TODO
 * authorEmail imythu@qq.com
 *
 * @author myth
 * @date 2019/8/11 0:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodStringKey {
    /**
     * 方法编号。
     * @return methodNo
     */
    String methodKey() default "";
}
