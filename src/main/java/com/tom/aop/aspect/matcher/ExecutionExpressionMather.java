package aop.aspect.matcher;

import java.lang.reflect.Method;

/**
 * 匹配execution表达式的匹配器
 */
public class ExecutionExpressionMather implements PointCutMatcher {

    /**
     * 使用正则匹配
     */
    @Override
    public boolean match(Method method, String expression) {
        //需要将() 进行转义
        expression = expression.replace("(", "[(]");
        expression = expression.replace(")", "[)]");
        return method.toString().matches(expression);
    }
}
