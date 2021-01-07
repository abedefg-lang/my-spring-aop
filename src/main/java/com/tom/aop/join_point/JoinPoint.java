package aop.join_point;

import lombok.Getter;

import java.lang.reflect.Method;

/**
 * 加入点
 * 封装了目标方法，参数，目标对象
 * 这里将目标方法的返回值  目标方法抛出的异常也封装在一起
 */
@Getter
public class JoinPoint implements Cloneable{

    /**目标方法*/
    protected Method method;

    /**目标对象*/
    protected Object target;

    /**传递的参数*/
    protected Object[] args;


    public JoinPoint(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
