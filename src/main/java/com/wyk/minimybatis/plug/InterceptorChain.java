package com.wyk.minimybatis.plug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/29 13:49
 * @Modified By:
 */
public class InterceptorChain {

    private final List<Interceptor> interceptors = new ArrayList<Interceptor>();

    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

}
