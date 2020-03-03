package com.wyk.minimybatis.config;

import com.wyk.minimybatis.executor.Executor;
import com.wyk.minimybatis.executor.SimpleExecutor;
import com.wyk.minimybatis.plug.InterceptorChain;
import com.wyk.minimybatis.proxy.MapperProxy;
import com.wyk.minimybatis.session.SqlSession;
import lombok.Data;

import java.lang.reflect.Proxy;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 18:35
 * @Modified By:
 */
@Data
public class Configuration {

    public static final String USER_NAME = "root";
    public static final String PASS_WORD = "root";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://192.168.111.128:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    private final MapperRegistory mapperRegistory;

    protected final InterceptorChain interceptorChain = new InterceptorChain();

    private String scanPackageName;

    public Configuration(String scanPackageName) {
        this.scanPackageName = scanPackageName;
        mapperRegistory = new MapperRegistory(scanPackageName);
    }

    public Executor newExecutor() {
        Executor executor = new SimpleExecutor(this);
        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(sqlSession, clazz));
    }


}
