package pers.tom.aop2.joinpoint;

import java.lang.reflect.Method;

/**
 * @author lijia
 * @description 通知织入点
 * @date 2021-04-26 11:54
 */
public class JoinPointImpl implements JoinPoint {

    /**织入目标方法*/
    private final Method method;

    /**执行目标方法对象*/
    private final Object target;

    /**执行目标方法参数*/
    private final Object[] args;

    public JoinPointImpl(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    @Override
    public Object[] getArgs() {
        return this.args;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object getTarget() {
        return this.target;
    }
}
