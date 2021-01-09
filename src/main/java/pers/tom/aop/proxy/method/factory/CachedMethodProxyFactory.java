package pers.tom.aop.proxy.method.factory;

import pers.tom.aop.proxy.method.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存的代理方法工厂
 */
public abstract class CachedMethodProxyFactory implements MethodProxyFactory{

    /**缓存*/
    private final Map<Method, MethodProxy> methodProxyCache = new HashMap<>(128);


    @Override
    public MethodProxy getMethodProxy(Method target) {
        MethodProxy mp = methodProxyCache.get(target);
        if(mp == null){
            mp = createMethodProxy(target);
            methodProxyCache.put(target, mp);
        }
        return mp;
    }

    /***
     * 如果缓存中没有数据会调用这个方法进行创建  然后存到缓存中
     */
    protected abstract MethodProxy createMethodProxy(Method target);
}
