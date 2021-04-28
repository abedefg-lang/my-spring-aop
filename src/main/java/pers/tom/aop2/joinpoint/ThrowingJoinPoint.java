package pers.tom.aop2.joinpoint;

import java.lang.reflect.Method;

/**
 * @author lijia
 * @description 异常织入点
 * @date 2021-04-26 11:58
 */
@Deprecated
public class ThrowingJoinPoint extends JoinPointImpl {

    /**抛出的异常*/
    private final Throwable throwable;

    public ThrowingJoinPoint(Method method, Object target, Object[] args, Throwable throwable) {
        super(method, target, args);
        this.throwable = throwable;
    }

    public ThrowingJoinPoint(JoinPointImpl joinPoint, Throwable throwable){
        this(joinPoint.getMethod(), joinPoint.getTarget(), joinPoint.getArgs(), throwable);
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
