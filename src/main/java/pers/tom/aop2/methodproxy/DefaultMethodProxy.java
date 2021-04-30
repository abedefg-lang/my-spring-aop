package pers.tom.aop2.methodproxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author lijia
 * @description 默认代理方法
 * @date 2021-04-30 16:14
 */
public class DefaultMethodProxy implements MethodProxy{

    /**目标方法*/
    private final Method targetMethod;

    public DefaultMethodProxy(Method targetMethod) {
        Objects.requireNonNull(targetMethod, "target method不能为null");
        this.targetMethod = targetMethod;
    }

    @Override
    public Method getMethod() {
        return this.targetMethod;
    }

    @Override
    public Object invoke(Object o, Object[] args) throws Throwable {
        return targetMethod.invoke(o, args);
    }
}
