package com.wyk.minimybatis.annotation;

import java.lang.annotation.*;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 18:55
 * @Modified By:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Select{

    String value();

}
