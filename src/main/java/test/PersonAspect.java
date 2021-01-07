package test;

import aop.annotation.Aspect;
import aop.annotation.Before;
import aop.join_point.JoinPoint;

import java.lang.reflect.Method;

@Aspect
public class PersonAspect {

    @Before("execution(public .* test.Person.*())")
    public void log(JoinPoint jp){
        Method method = jp.getMethod();
        System.out.println(method.getDeclaringClass().getName()+"中的"+method.getName()+"开始执行");
    }

}
