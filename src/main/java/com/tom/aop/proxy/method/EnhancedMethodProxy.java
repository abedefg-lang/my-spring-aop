package aop.proxy.method;


import aop.aspect.advice.Advice;
import aop.aspect.advice.AdviceType;
import aop.join_point.JoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 目标方法的增强代理
 * 将前置，后置，异常，返回通知和需要代理的方法封装在一起
 */
public class EnhancedMethodProxy extends MethodProxy{

    /**前置通知*/
    private List<Advice> beforeAdvices;

    /**异常通知*/
    private List<Advice> afterThrowingAdvices;

    /**返回通知*/
    private List<Advice> afterReturningAdvices;

    /**后置通知*/
    private List<Advice> afterAdvices;


    public EnhancedMethodProxy(Method method) {
        super(method);
    }

    /**
     * 重写invoke
     */
    @Override
    public Object invoke(Object o, Object[] args) throws Throwable {
        //创建加入点对象
        JoinPoint joinPoint = new JoinPoint(getMethod(), o, args);
        //执行前置通知
        invokeAdvices(beforeAdvices, joinPoint, null);
        Object result = null;
        Throwable throwable = null;
        try{
            result = super.invoke(o, args);
        }catch (Throwable t){
            throwable = t;
            if(t instanceof InvocationTargetException){
                throwable = ((InvocationTargetException)t).getTargetException();
            }
            //执行异常通知
            invokeAdvices(afterThrowingAdvices, joinPoint, throwable);
        }finally {
            //执行返回通知
            invokeAdvices(afterReturningAdvices, joinPoint, result);
        }
        //执行后置通知
        invokeAdvices(afterAdvices, joinPoint, result);
        //判断throwable是否需要抛出
        if(throwable != null) throw throwable;
        return result;
    }

    /**
     * 将一个非环绕通知加入到对应的集合
     */
    public void addAdvice(Advice advice, AdviceType type){
        switch (type){
            case BEFORE:
                if(beforeAdvices == null) beforeAdvices = new ArrayList<>(4);
                beforeAdvices.add(advice);
                break;
            case AFTER_THROWING:
                if(afterThrowingAdvices == null) afterThrowingAdvices = new ArrayList<>(4);
                afterThrowingAdvices.add(advice);
                break;
            case AFTER_RETURNING:
                if(afterReturningAdvices == null) afterReturningAdvices = new ArrayList<>(4);
                afterReturningAdvices.add(advice);
                break;
            case AFTER:
                if(afterAdvices == null) afterAdvices = new ArrayList<>(4);
                afterAdvices.add(advice);
        }
    }


    public void setBeforeAdvices(List<Advice> beforeAdvices) {
        this.beforeAdvices = beforeAdvices;
    }

    public void setAfterThrowingAdvices(List<Advice> afterThrowingAdvices) {
        this.afterThrowingAdvices = afterThrowingAdvices;
    }

    public void setAfterReturningAdvices(List<Advice> afterReturningAdvices) {
        this.afterReturningAdvices = afterReturningAdvices;
    }

    public void setAfterAdvices(List<Advice> afterAdvices) {
        this.afterAdvices = afterAdvices;
    }

    /**
     * 执行通知
     */
    private void invokeAdvices(List<Advice> adviceList, JoinPoint joinPoint, Object object) throws Throwable {
        if(adviceList != null && !adviceList.isEmpty()){
            Object[] args = null;
            Class<?>[] parameterTypes;
            for(Advice advice : adviceList){
                parameterTypes = advice.getMethod().getParameterTypes();
                //获取可执行参数
                if(parameterTypes.length != 0) {
                    if (JoinPoint.class == parameterTypes[0]){
                        args = parameterTypes.length == 1 ? new Object[]{joinPoint} : new Object[]{joinPoint, object};
                    }else{
                        args = parameterTypes.length == 1 ? new Object[]{object} : new Object[]{object, joinPoint};
                    }
                }
                advice.invoke(args);
            }
        }
    }



}
