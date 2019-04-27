package com.wyk.minimybatis.proxy;

import com.wyk.minimybatis.config.MapperRegistory;
import com.wyk.minimybatis.session.SqlSession;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/27 13:33
 * @Modified By:
 */
@Data
public class MapperProxy implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;

    private final Class clazz;

    public MapperProxy(SqlSession sqlSession, Class clazz) {
        this.sqlSession = sqlSession;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String s = clazz.getName() + "." + method.getName();
        MapperRegistory.MapperData data = this.sqlSession.getConfiguration().getMapperRegistory().getMethodMaping()
                .get(s);
        if (null != data){
            return this.sqlSession.selectOne(data,args);
        }

        return method.invoke(proxy, args);
    }
}
