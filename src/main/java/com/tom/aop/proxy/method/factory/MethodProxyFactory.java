package aop.proxy.method.factory;

import aop.proxy.method.MethodProxy;

import java.lang.reflect.Method;

/**
 * 获取方法代理的工厂
 */
public interface MethodProxyFactory {

    /**
     * 获取指定方法的代理
     */
    MethodProxy getMethodProxy(Method target);
}
