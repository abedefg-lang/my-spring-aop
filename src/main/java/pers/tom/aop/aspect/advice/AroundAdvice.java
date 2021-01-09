package pers.tom.aop.aspect.advice;

import pers.tom.aop.join_point.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 环绕通知
 */
public class AroundAdvice extends Advice{


    public AroundAdvice(Method method, Object instance) {
        super(method, instance);
    }

    /**
     * 执行环绕通知
     */
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        //判断参数数量
        Object result;
        if(getMethod().getParameterTypes().length == 0){
            result = super.invoke(null);
        }else{
            result = super.invoke(new Object[]{pjp});
        }
        return result;
    }
}
