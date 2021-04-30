package pers.tom.aop2.methodproxy.factory;

import pers.tom.aop2.advice.Advice;
import pers.tom.aop2.advice.AdvicesProvider;
import pers.tom.aop2.methodproxy.AdviceMethodProxy;
import pers.tom.aop2.methodproxy.DefaultMethodProxy;
import pers.tom.aop2.methodproxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lijia
 * @description advice代理方法工厂
 * @date 2021-04-30 16:01
 */
public class AdviceMethodProxyFactory implements MethodProxyFactory {

    /**advices*/
    private final List<Advice> advices;

    /**代理方法缓存*/
    private final Map<Method, MethodProxy> methodProxyCache;

    public AdviceMethodProxyFactory(AdvicesProvider provider){
        this(provider.getAdvices());
    }

    public AdviceMethodProxyFactory(List<Advice> advices){
        this.advices = advices;
        this.methodProxyCache = new ConcurrentHashMap<>();
    }

    @Override
    public MethodProxy getMethodProxy(Method method) {

        Objects.requireNonNull(method, "method不能为null");

        //先查看缓存中是否有代理方法
        MethodProxy methodProxy = methodProxyCache.get(method);
        if(methodProxy == null){
            //如果为null 找到匹配该方法的通知
            List<Advice> matchedAdvices = new ArrayList<>();
            for(Advice advice : advices){
                if(advice.match(method)){
                    matchedAdvices.add(advice);
                }
            }

            methodProxy = matchedAdvices.isEmpty() ? new DefaultMethodProxy(method) : new AdviceMethodProxy(matchedAdvices, method);

        }

        return methodProxy;
    }
}
