package pers.tom.aop2.methodproxy.factory;

import pers.tom.aop2.methodproxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lijia
 * @description 代理方法工厂
 * @date 2021-04-30 15:45
 */
public interface MethodProxyFactory {

    /**
     * 获取指定方法的代理方法
     * @param method method
     * @return methodProxy
     */
    MethodProxy getMethodProxy(Method method);
}
