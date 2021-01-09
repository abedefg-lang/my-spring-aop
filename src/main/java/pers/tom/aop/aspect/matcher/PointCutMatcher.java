package pers.tom.aop.aspect.matcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * 切点匹配器
 * 对于不同的表达式前缀 匹配的规则不同
 */
public interface PointCutMatcher {

    /**
     * 匹配逻辑
     * @param method        需要匹配的方法
     * @param expression    表达式
     * @return 返回是否匹配成功
     */
    boolean match(Method method, String expression);

    /**
     * 匹配类型
     */
    default boolean matchType(Class<?> type, String typeExpression){
        return type.getName().matches(typeExpression);
    }

    /**
     * 匹配注解
     */
    default boolean matchAnnotation(AnnotatedElement element, String annExpression){
        Annotation[] annotations = element.getDeclaredAnnotations();
        if(annotations.length != 0){
            for(Annotation ann : annotations){
                if(matchType(ann.annotationType(), annExpression)){
                    return true;
                }
            }
        }
        return false;
    }
}
