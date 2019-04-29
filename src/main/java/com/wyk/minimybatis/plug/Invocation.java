package com.wyk.minimybatis.plug;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/29 13:49
 * @Modified By:
 */

public class Invocation {

    private Object target;

    private Object[] args;

    private Method method;

    public Invocation(Object target, Object[] args, Method method) {
        this.target = target;
        this.args = args;
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public Object[] getArgs() {
        return args;
    }

    public Method getMethod() {
        return method;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
