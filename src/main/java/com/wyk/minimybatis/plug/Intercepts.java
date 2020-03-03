package com.wyk.minimybatis.plug;

import java.lang.annotation.*;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2020/3/2 18:32
 * @Modified By:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Intercepts {
    Signature[] value();
}

