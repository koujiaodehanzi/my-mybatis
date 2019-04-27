package com.wyk.minimybatis.executor;

import com.wyk.minimybatis.config.Configuration;
import com.wyk.minimybatis.config.MapperRegistory;
import com.wyk.minimybatis.statement.StatementHandler;
import lombok.Data;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/27 13:59
 * @Modified By:
 */
@Data
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperRegistory.MapperData mapperData, Object... parameter) throws Exception{
        StatementHandler handler = new StatementHandler(configuration);

        return handler.query(mapperData, parameter);
    }
}
