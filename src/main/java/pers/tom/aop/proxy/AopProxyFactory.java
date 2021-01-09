package pers.tom.aop.proxy;

/**
 * 代理工厂
 */
public interface AopProxyFactory {

    Object getProxy(Object target);
}
