package pers.tom.aop2.joinpoint;

import java.lang.reflect.Method;

/**
 * @author lijia
 * @description 通知织入点
 * @date 2021-04-26 14:08
 */
public interface JoinPoint {

    /**
     * 获取执行目标方法参数
     * @return args
     */
    Object[] getArgs();

    /**
     * 获取目标方法
     * @return method
     */
    Method getMethod();

    /**
     * 获取目标对象
     * @return target
     */
    Object getTarget();

}
