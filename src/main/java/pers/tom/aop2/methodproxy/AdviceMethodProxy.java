package pers.tom.aop2.methodproxy;

import pers.tom.aop2.advice.Advice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lijia
 * @description 基于通知的代理方法
 * @date 2021-04-30 15:45
 */
public class AdviceMethodProxy implements MethodProxy{

    /**代理目标方法*/
    private final Method targetMethod;

    public AdviceMethodProxy(List<Advice> matchedAdvices, Method targetMethod){
        this.targetMethod = targetMethod;
    }

    @Override
    public Method getMethod() {
        return this.targetMethod;
    }

    @Override
    public Object invoke(Object o, Object[] args) throws Throwable {
        return null;
    }
}
