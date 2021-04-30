package pers.tom.aop2.advice;

import pers.tom.aop2.joinpoint.JoinPoint;

/**
 * @author lijia
 * @description 返回通知
 * @date 2021-04-28 20:37
 */
public interface AfterReturningAdvice extends Advice{

    /**
     * 执行返回通知
     * @param joinPoint 当前通知织入点
     * @param result 返回结果
     * @throws Throwable throwable
     */
    void afterReturning(JoinPoint joinPoint, Object result) throws Throwable;
}
