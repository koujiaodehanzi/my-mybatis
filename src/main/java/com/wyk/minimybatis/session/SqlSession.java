package com.wyk.minimybatis.session;

import com.wyk.minimybatis.config.Configuration;
import com.wyk.minimybatis.config.MapperRegistory;

import java.sql.Connection;
import java.util.List;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/27 13:12
 * @Modified By:
 */
public interface SqlSession {

    <T> T getMapper(Class<T> var1);

    <T> T selectOne(MapperRegistory.MapperData mapperData, Object... parameter) throws Exception;

    <E> List<E> selectList(MapperRegistory.MapperData mapperData, Object... parameter) throws Exception;

    int insert(String var1);

    Configuration getConfiguration();

    Connection getConnection();

}
