package aop.proxy;

import aop.proxy.method.factory.MethodProxyFactory;
import lombok.Data;

import java.lang.reflect.Proxy;

/**
 * 基于jdk动态代理的工厂
 * jdk的动态代理创建的代理对象与目标对象是兄弟关系 两个对象是相同级别的
 * 代理对象实现了目标对象实现的所有接口
 * 如果需要被代理的方法都是重写接口的方法  可以使用这个工厂
 */
@Data
public class JdkDynamicAopProxyFactory implements AopProxyFactory{

    public MethodProxyFactory factory;

    public JdkDynamicAopProxyFactory(MethodProxyFactory factory){
        this.factory = factory;
    }

    @Override
    public Object getProxy(Object target) {
        Class<?> tc = target.getClass();
        //使用jdk的动态代理创建
        return Proxy.newProxyInstance(tc.getClassLoader(), tc.getInterfaces(), (proxy, method, args) -> factory.getMethodProxy(method).invoke(target, args));
    }
}
