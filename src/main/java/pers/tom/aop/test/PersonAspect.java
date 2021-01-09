package pers.tom.aop.test;

import pers.tom.aop.annotation.After;
import pers.tom.aop.annotation.Aspect;
import pers.tom.aop.annotation.Before;
import pers.tom.aop.annotation.PointCut;
import pers.tom.aop.join_point.JoinPoint;

import java.lang.reflect.Method;

@Aspect
public class PersonAspect {

    @PointCut("execution(public .* com.tom.aop.test.Person.*())")
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
