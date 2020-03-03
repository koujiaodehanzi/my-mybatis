package com.wyk.minimybatis.plug;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/29 13:49
 * @Modified By:
 */
public abstract class Interceptor{

    abstract Object intercept(Invocation invocation) throws Throwable;

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
