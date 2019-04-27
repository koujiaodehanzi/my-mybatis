package com.wyk.minimybatis.annotation;

import java.lang.annotation.*;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 19:00
 * @Modified By:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {

    String value();

}
