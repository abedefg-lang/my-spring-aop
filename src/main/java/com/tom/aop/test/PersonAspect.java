package test;

import aop.annotation.After;
import aop.annotation.Aspect;
import aop.annotation.Before;
import aop.annotation.PointCut;
import aop.join_point.JoinPoint;

import java.lang.reflect.Method;

@Aspect
public class PersonAspect {

    @PointCut("execution(public .* test.Person.*())")
    public void pointCut(){

    }


    @Before("pointCut")
    public void before(JoinPoint jp){
        Method method = jp.getMethod();
        System.out.println(method.getDeclaringClass().getName()+"中的"+method.getName()+"开始执行");
    }

    @After("pointCut")
    public void after(JoinPoint jp){
        Method method = jp.getMethod();
        System.out.println(method.getDeclaringClass().getName()+"中的"+method.getName()+"执行结束");
    }

}
