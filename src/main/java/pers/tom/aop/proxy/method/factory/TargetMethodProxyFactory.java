package pers.tom.aop.proxy.method.factory;

import pers.tom.aop.aspect.AspectDefinition;
import pers.tom.aop.aspect.advice.Advice;
import pers.tom.aop.aspect.advice.AdviceType;
import pers.tom.aop.aspect.advice.AroundAdvice;
import pers.tom.aop.aspect.parser.AspectDefinitionParser;
import pers.tom.aop.proxy.method.EnhancedMethodProxy;
import pers.tom.aop.proxy.method.MethodProxy;
import pers.tom.aop.proxy.method.TargetMethodProxy;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class TargetMethodProxyFactory extends CachedMethodProxyFactory{

    /**切面信息*/
    private final List<AspectDefinition> aspectDefinitionList;

    public TargetMethodProxyFactory(AspectDefinitionParser parser){
        aspectDefinitionList = parser.parse();
    }

    @Override
    protected MethodProxy createMethodProxy(Method target) {
        Objects.requireNonNull(target, "target不能为null");
        //环绕通知集合
        List<AroundAdvice> adviceList = new LinkedList<>();
        //增强方法代理
        EnhancedMethodProxy emp = new EnhancedMethodProxy(target);
        for(AspectDefinition aspectDefinition : aspectDefinitionList){
            for(Advice advice : aspectDefinition.getAdvices()){
                if(advice.getPointCutExpression().match(target)){
                    //判断这个通知是否是环绕通知  如果是环绕通知添加到上面的list
                    if(AdviceType.AROUND == advice.getAdviceType()){
                        adviceList.add(0, new AroundAdvice(advice.getMethod(), advice.getInstance()));
                    }else{
                        //如果不是添加到增强通知中
                        emp.addAdvice(advice, advice.getAdviceType());
                    }
                }
            }
        }
        return new TargetMethodProxy(emp, adviceList);
    }
}
