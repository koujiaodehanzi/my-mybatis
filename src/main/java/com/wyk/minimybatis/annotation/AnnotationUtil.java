package com.wyk.minimybatis.annotation;

import javafx.scene.control.Tab;

import java.lang.annotation.Annotation;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 19:45
 * @Modified By:
 */
public class AnnotationUtil {

    public static String getAnnotationValue(Annotation annotation){
        String value = null;
        if (annotation instanceof Select){
            value = ((Select) annotation).value();
        }else if (annotation instanceof Insert){
            value = ((Insert) annotation).value();
        }else if (annotation instanceof Table){
            value = ((Table) annotation).value();
        }
        return value;
    }

}
