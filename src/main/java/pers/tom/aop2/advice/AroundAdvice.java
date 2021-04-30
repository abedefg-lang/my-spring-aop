package pers.tom.aop2.advice;

import pers.tom.aop2.joinpoint.ProceedingJoinPoint;

/**
 * @author lijia
 * @description 环绕通知
 * @date 2021-04-30 15:38
 */
public interface AroundAdvice extends Advice{

    /**
     * 执行环绕通知
     * @param joinPoint 可执行加入点
     * @return result
     * @throws Throwable throwable
     */
    Object around(ProceedingJoinPoint joinPoint) throws Throwable;
}
