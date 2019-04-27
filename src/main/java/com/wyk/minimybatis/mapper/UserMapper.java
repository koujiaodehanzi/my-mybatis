package com.wyk.minimybatis.mapper;

import com.wyk.minimybatis.annotation.Insert;
import com.wyk.minimybatis.annotation.Select;
import com.wyk.minimybatis.annotation.Table;
import com.wyk.minimybatis.entity.User;

import java.util.List;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 18:42
 * @Modified By:
 */
@Table("user")
public interface UserMapper {

    @Select("select * from %s where id = %s")
    User selectByPrimiryKey(Integer userId);

    @Select("select * from %s")
    List<User> queryAllUser();

    @Insert("insert into %s values (%s)")
    int insert(User user);

}
