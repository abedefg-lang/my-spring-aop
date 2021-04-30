package pers.tom.aop2;

/**
 * @author lijia
 * @description aop代理工厂
 * @date 2021-04-30 15:48
 */
public interface AopProxyFactory {

    /**
     * 给目标对象创建代理对象
     * @param target target
     * @return proxy
     */
    Object getProxy(Object target);
}
