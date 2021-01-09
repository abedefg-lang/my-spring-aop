package pers.tom.aop.join_point;


import pers.tom.aop.proxy.method.MethodProxy;


/**
 * 可执行joinPoint
 */
public class ProceedingJoinPoint extends JoinPoint{

    /**代理方法*/
    protected MethodProxy methodProxy;

    public ProceedingJoinPoint(MethodProxy methodProxy, Object target, Object[] args) {
        super(methodProxy.getMethod(), target, args);
        this.methodProxy = methodProxy;
    }


    public Object proceed(Object[] objects) throws Throwable {
        //判断与之前记录的参数长度是否相同  快速失败
        if(args != null && args.length != objects.length){
            throw new RuntimeException("检查参数....");
        }
        args = objects;
        return methodProxy.invoke(target, args);
    }


    public Object proceed() throws Throwable {
        return this.proceed(args);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
