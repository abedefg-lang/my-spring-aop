package pers.tom.aop2.methodproxy;

import java.lang.reflect.Method;

/**
 * @author lijia
 * @description 代理方法
 * @date 2021-04-30 15:42
 */
public interface MethodProxy {

    /**
     * 获取被代理的方法
     * @return method
     */
    Method getMethod();

    /**
     * 执行
     * @param o 可执行实例
     * @param args 执行参数
     * @return result
     * @throws Throwable throwable
     */
    Object invoke(Object o, Object[] args) throws Throwable;
}
