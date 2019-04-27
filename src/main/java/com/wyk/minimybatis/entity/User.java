package com.wyk.minimybatis.entity;

import lombok.Data;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 18:42
 * @Modified By:
 */
@Data
public class User {

    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
