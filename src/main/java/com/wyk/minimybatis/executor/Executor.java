package com.wyk.minimybatis.executor;

import com.wyk.minimybatis.config.MapperRegistory;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/27 13:15
 * @Modified By:
 */
public interface Executor {

    <T> T query(MapperRegistory.MapperData mapperData, Object... parameter) throws Exception ;

}
