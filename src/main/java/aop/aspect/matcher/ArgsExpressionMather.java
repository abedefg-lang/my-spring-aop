package aop.aspect.matcher;

import java.lang.reflect.Method;

/**
 * args规则的匹配器
 */
public class ArgsExpressionMather implements PointCutMatcher {

    @Override
    public boolean match(Method method, String expression) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] typeStrs = expression.split(",");
        //如果长度不同 直接返回false  快速失败
        if(parameterTypes.length != typeStrs.length) return false;
        for(int i = 0 ; i < parameterTypes.length ; i ++){
            if(!matchType(parameterTypes[i], typeStrs[i].trim())){
                return false;
            }
        }
        return true;
    }
}
