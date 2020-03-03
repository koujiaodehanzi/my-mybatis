package com.wyk.minimybatis.plug;


import org.apache.ibatis.plugin.PluginException;
import org.apache.ibatis.reflection.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/29 13:49
 * @Modified By:
 */
public class Plugin implements InvocationHandler {
    private final Object target;
    private final Interceptor interceptor;
    private final Map<Class<?>, Set<Method>> signatureMap;

    private Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    public static Object wrap(Object target, Interceptor interceptor) {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
        return interfaces.length > 0 ? Proxy.newProxyInstance(type.getClassLoader(), interfaces, new Plugin(target, interceptor, signatureMap)) : target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Set<Method> methods = this.signatureMap.get(method.getDeclaringClass());
            return methods != null && methods.contains(method) ? this.interceptor.intercept(new Invocation(this.target, args, method)) : method.invoke(this.target, args);
        } catch (Exception var5) {
            throw ExceptionUtil.unwrapThrowable(var5);
        }
    }

    private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) {
        Intercepts interceptsAnnotation = (Intercepts)interceptor.getClass().getAnnotation(Intercepts.class);
        if (interceptsAnnotation == null) {
            throw new PluginException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        } else {
            Signature[] sigs = interceptsAnnotation.value();
            Map<Class<?>, Set<Method>> signatureMap = new HashMap();
            Signature[] var4 = sigs;
            int var5 = sigs.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Signature sig = var4[var6];
                Set methods = (Set)signatureMap.computeIfAbsent(sig.type(), (k) -> new HashSet());

                try {
                    Method method = sig.type().getMethod(sig.method(), sig.args());
                    methods.add(method);
                } catch (NoSuchMethodException var10) {
                    throw new PluginException("Could not find method on " + sig.type() + " named " + sig.method() + ". Cause: " + var10, var10);
                }
            }

            return signatureMap;
        }
    }

    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        HashSet interfaces;
        for(interfaces = new HashSet(); type != null; type = type.getSuperclass()) {
            Class[] var3 = type.getInterfaces();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<?> c = var3[var5];
                if (signatureMap.containsKey(c)) {
                    interfaces.add(c);
                }
            }
        }

        return (Class[])interfaces.toArray(new Class[interfaces.size()]);
    }
}
