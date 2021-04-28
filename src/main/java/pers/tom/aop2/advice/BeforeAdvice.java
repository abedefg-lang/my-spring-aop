package pers.tom.aop2.advice;

import pers.tom.aop2.joinpoint.JoinPoint;

/**
 * @author lijia
 * @description 前置通知
 * @date 2021-04-26 14:12
 */
public interface BeforeAdvice extends Advice{

    /**
     * 执行前置通知
     * @param joinPoint 当前通知织入点
     * @throws Throwable throwable
     */
    void before(JoinPoint joinPoint) throws Throwable;
}
