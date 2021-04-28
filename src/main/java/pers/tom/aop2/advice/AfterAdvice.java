package pers.tom.aop2.advice;

import pers.tom.aop2.joinpoint.JoinPoint;

/**
 * @author lijia
 * @description 后置通知
 * @date 2021-04-28 14:25
 */
public interface AfterAdvice extends Advice{

    /**
     * 执行后置通知
     * @param joinPoint 当前通知织入点
     * @param result 返回结果
     * @throws Throwable throwable
     */
    void after(JoinPoint joinPoint, Object result) throws Throwable;
}
