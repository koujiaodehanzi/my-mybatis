package com.wyk.minimybatis.result;

import com.wyk.minimybatis.config.Configuration;
import com.wyk.minimybatis.config.MapperRegistory;
import com.wyk.minimybatis.utils.StringUtil;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/27 13:51
 * @Modified By:
 */
public class ResultSetHandler {

    private final Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 将返回的结果集封装为Bean
     * @param pstmt
     * @param mapperData
     * @param <E>
     * @return
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public <E> E handle(PreparedStatement pstmt, MapperRegistory.MapperData mapperData) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = new DefaultObjectFactory().create(mapperData.getType());

        ResultSet rs = pstmt.getResultSet();
        while (rs.next()){
            int i = 0;
            for (Field field : obj.getClass().getDeclaredFields()){
                setValue(obj, field, rs, i);
            }
        }

        return (E) obj;
    }


    private void setValue(Object obj, Field field, ResultSet rs, int i) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Method method = obj.getClass().getMethod("set" + StringUtil.firstCharSwapHigh(field.getName()), field.getType());
        method.invoke(obj, getResult(field, rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if(String.class == type){
            return rs.getString(field.getName());
        }
        return rs.getString(field.getName());
    }


}
