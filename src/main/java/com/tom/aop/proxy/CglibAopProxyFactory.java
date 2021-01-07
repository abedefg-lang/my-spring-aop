package aop.proxy;

import aop.proxy.method.factory.MethodProxyFactory;
import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 基于cglib创建代理的工厂
 * cglib创建的代理对象与目标对象是父子关系
 * 代理对象继承了目标对象
 * 如果需要被代理的方法不是重写的(是当前目标对象独有的方法) 可以使用这个工厂
 */
@Data
public class CglibAopProxyFactory implements AopProxyFactory {

    public MethodProxyFactory factory;

    public CglibAopProxyFactory(MethodProxyFactory factory){
        this.factory = factory;
    }

    @Override
    public Object getProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> factory.getMethodProxy(method).invoke(target, objects));
        return enhancer.create();
    }
}
