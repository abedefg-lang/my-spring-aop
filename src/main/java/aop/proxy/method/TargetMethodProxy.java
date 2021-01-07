package aop.proxy.method;

import aop.aspect.advice.AroundAdvice;
import aop.join_point.ProceedingJoinPoint;

import java.util.List;

/**
 *  目标方法代理  将环绕通知与被代理的方法封装在一起
 */
public class TargetMethodProxy extends MethodProxy{

    /**目标方法*/
    private final MethodProxy methodProxy;

    /**环绕通知链*/
    private final List<AroundAdvice> aroundAdviceList;

    /**记录环绕通通知链的位置*/
    private int nextIdx;


    public TargetMethodProxy(MethodProxy methodProxy, List<AroundAdvice> aroundAdvices) {
        super(methodProxy.getMethod());
        this.methodProxy = methodProxy;
        this.aroundAdviceList = aroundAdvices;
    }

    @Override
    public Object invoke(Object o, Object[] args) throws Throwable {
        Object result;
        //判断环绕通知执行完没有
        if(nextIdx == aroundAdviceList.size()){
            //重置index
            nextIdx = 0;
            //执行methodProxy
            result = methodProxy.invoke(o, args);
        }else{
            //执行环绕通知链
            ProceedingJoinPoint pjp = new ProceedingJoinPoint(this, o, args);
            result = aroundAdviceList.get(nextIdx ++).invoke(pjp);
        }
        return result;
    }



}
