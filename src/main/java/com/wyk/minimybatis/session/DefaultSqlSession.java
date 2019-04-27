package com.wyk.minimybatis.session;

import com.wyk.minimybatis.config.Configuration;
import com.wyk.minimybatis.config.MapperRegistory;
import com.wyk.minimybatis.executor.Executor;
import com.wyk.minimybatis.proxy.MapperProxy;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/27 13:13
 * @Modified By:
 */
@Data
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    @Override
    public <T> T selectOne(MapperRegistory.MapperData mapperData, Object... parameter) throws Exception {

        return executor.query(mapperData, parameter);
    }

    @Override
    public <E> List<E> selectList(MapperRegistory.MapperData mapperData, Object... parameter) throws Exception {
        return executor.query(mapperData, parameter);
    }

    @Override
    public int insert(String var1) {
        return 0;
    }


    @Override
    public Connection getConnection() {
        return null;
    }
}
