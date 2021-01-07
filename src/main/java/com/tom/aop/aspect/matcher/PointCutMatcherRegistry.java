package aop.aspect.matcher;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 切点匹配器注册表
 */
public class PointCutMatcherRegistry {

    /**存储matcher*/
    private static Map<String, PointCutMatcher> matcherMap = new HashMap<>();

    static {
        //实现args规则的匹配器
        matcherMap.put("args", new ArgsExpressionMather());

        //实现execution规则的匹配器
        matcherMap.put("execution", new ExecutionExpressionMather());

        //实现@annotation规则的匹配器
        matcherMap.put("@annotation", new PointCutMatcher() {
            @Override
            public boolean match(Method method, String expression) {
                return matchAnnotation(method, expression);
            }
        });

        //实现within规则的匹配器
        matcherMap.put("within", new PointCutMatcher() {
            @Override
            public boolean match(Method method, String expression) {
                return matchType(method.getDeclaringClass(), expression);
            }
        });

        //实现@within规则的匹配器
        matcherMap.put("@within", new PointCutMatcher() {
            @Override
            public boolean match(Method method, String expression) {
                return matchAnnotation(method.getDeclaringClass(), expression);
            }
        });
    }

    /**
     * 通过对应的规则获取匹配器
     */
    public static PointCutMatcher getMatcher(String rules){
        return matcherMap.get(rules);
    }

    /**
     * 注册一个matcher
     */
    public static void register(String rules, PointCutMatcher matcher){
        matcherMap.put(rules, matcher);
    }

    /**
     * 获取所有的匹配规则
     * 也就是获取所有的key
     */
    public static String[] getRules(){
        return matcherMap.keySet().toArray(new String[0]);
    }
}
