package com.wyk;

import static org.junit.Assert.assertTrue;

import com.wyk.minimybatis.config.MapperRegistory;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws ClassNotFoundException {
        String rootPath = this.getClass().getResource("/").getPath();


        MapperRegistory registory = new MapperRegistory(rootPath+"com/wyk/minimybatis/mapper");
        Map<String, MapperRegistory.MapperData> map = registory.getMethodMaping();
        for (Map.Entry<String, MapperRegistory.MapperData> entry : map.entrySet()){
            System.out.println("methodName" + entry.getKey() + "|| sql:" + entry.getValue().getSql()+ "|| className:"
                    + entry.getValue().getType().getSimpleName());
        }
    }


}
