package aop.aspect.advice;

import aop.aspect.point_cut.PointCutExpression;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * 将通知信息进行封装
 */
@Getter
@Setter
public class Advice {

    /**切点表达式对象*/
    private PointCutExpression pointCutExpression;

    /**方法*/
    private Method method;

    /**能够执行当前方法的实例*/
    private Object instance;

    /**通知类型*/
    private AdviceType adviceType;


    public Advice(Method method, Object instance){
        validate(method);
        this.method = method;
        this.instance = instance;
    }

    /**
     * 执行当前通知逻辑
     */
    public Object invoke(Object[] args) throws Throwable{
        return method.invoke(instance, args);
    }

    /**
     * 验证当前是否符合当前通知的参数
     */
    protected void validate(Method method){
        Class<?>[] parameterTypes = method.getParameterTypes();
        if(parameterTypes.length > 2){
            throw new RuntimeException(method+"  参数列表长度不能超过2");
        }
    }

}
