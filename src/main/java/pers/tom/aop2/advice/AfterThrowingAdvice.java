package pers.tom.aop2.advice;


import pers.tom.aop2.joinpoint.JoinPoint;

/**
 * @author lijia
 * @description 异常通知
 * @date 2021-04-28 14:26
 */
public interface AfterThrowingAdvice extends Advice{

    /**
     * 执行返回通知
     * @param joinPoint 当前通知织入点
     * @param throwable throwable
     * @throws Throwable throwable
     */
    void afterThrowing(JoinPoint joinPoint, Throwable throwable) throws Throwable;
}
