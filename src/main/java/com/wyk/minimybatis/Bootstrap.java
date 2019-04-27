package com.wyk.minimybatis;

import com.wyk.minimybatis.config.Configuration;
import com.wyk.minimybatis.config.MapperRegistory;
import com.wyk.minimybatis.entity.User;
import com.wyk.minimybatis.executor.SimpleExecutor;
import com.wyk.minimybatis.mapper.UserMapper;
import com.wyk.minimybatis.session.DefaultSqlSession;

import java.util.List;
import java.util.Map;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 22:47
 * @Modified By:
 */
public class Bootstrap {

    public static void main(String[] args) {

        /*MapperRegistory registory = new MapperRegistory("com.wyk.minimybatis.mapper");
        Map<String, MapperRegistory.MapperData> map = registory.getMethodMaping();
        for (Map.Entry<String, MapperRegistory.MapperData> entry : map.entrySet()){
            System.out.println("methodName" + entry.getKey() + "|| sql:" + entry.getValue().getSql()+ "|| className:"
                    + entry.getValue().getType().getSimpleName());
        }*/


        Configuration configuration = new Configuration("com.wyk.minimybatis.mapper");
        DefaultSqlSession sqlSession = new DefaultSqlSession(configuration, new SimpleExecutor(configuration));
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimiryKey(1);
        System.out.println(user);

    }

}
