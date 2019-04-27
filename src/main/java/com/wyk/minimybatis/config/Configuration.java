package com.wyk.minimybatis.config;

import lombok.Data;

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

    private String scanPackageName;

    public Configuration(String scanPackageName) {
        this.scanPackageName = scanPackageName;
        mapperRegistory = new MapperRegistory(scanPackageName);
    }
}
