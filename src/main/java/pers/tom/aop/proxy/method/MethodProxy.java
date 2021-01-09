package pers.tom.aop.proxy.method;

import java.lang.reflect.Method;

/**
 * 方法静态代理
 */
public class MethodProxy {

    private final Method method;

    public MethodProxy(Method method){
        this.method = method;
    }

    public Object invoke(Object o, Object[] args) throws Throwable{
        return method.invoke(o, args);
    }

    public Method getMethod() {
        return method;
    }
}
