package pers.tom.aop2.joinpoint;

import java.lang.reflect.Method;

/**
 * @author lijia
 * @description 返回织入点
 * @date 2021-04-26 11:58
 */
@Deprecated
public class ReturningJoinPoint extends JoinPointImpl {

    /**返回结果*/
    private final Object result;

    public ReturningJoinPoint(Method method, Object target, Object[] args, Object result) {
        super(method, target, args);
        this.result = result;
    }

    public ReturningJoinPoint(JoinPointImpl joinPoint, Object result){
        this(joinPoint.getMethod(), joinPoint.getTarget(), joinPoint.getArgs(), result);
    }

    public Object getResult() {
        return result;
    }
}
