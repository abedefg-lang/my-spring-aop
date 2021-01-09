package pers.tom.aop.utils;

import pers.tom.aop.aspect.matcher.PointCutMatcherRegistry;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PointCutExpressionUtils{

    /**匹配最基本表达式*/
    public static final Pattern SIMPLE_EXPRESSION_PATTERN;

    static {
        StringBuilder builder = new StringBuilder();
        String[] rules = PointCutMatcherRegistry.getRules();
        for(String rule : rules){
            builder.append(rule).append("|");
        }
        SIMPLE_EXPRESSION_PATTERN = Pattern.compile(builder.substring(0, builder.length()-1));
    }

    /**
     * 切点表达式的简单验证
     * 验证括号
     */
    public static void simpleValidation(String pointCutExpression){
        Objects.requireNonNull(pointCutExpression, "切点表达式不能为null");
        pointCutExpression = pointCutExpression.trim();
        if(pointCutExpression.length() != 0) {
            int count = 0;
            for(int i = 0 ; i < pointCutExpression.length() ; i ++){
                if('(' == pointCutExpression.charAt(i)){
                    count ++;
                }else if(')' == pointCutExpression.charAt(i)){
                    count --;
                }
            }
            if(count != 0){
                //如果count 不等于0说明括号不正确抛出异常
                throw new RuntimeException("检查表达式: " + pointCutExpression);
            }
        }
    }

    /**
     * 将一个完整的表达式解析成最基本的表达式形式
     * 比如"execution(...) && args(...)" 需要返回 "execution(...)","args(...)"
     * 由于这个方法需要返回两个内容  所以采用传递参数的形式
     * @param pointCutExpression 完整切点表达式
     * @param expressions       用来存储基本表达式的集合
     * @param strings           用来存储连接字符的集合
     */
    public static void parseSimpleExpression(String pointCutExpression, List<String> expressions, List<String> strings){
        Matcher matcher = SIMPLE_EXPRESSION_PATTERN.matcher(pointCutExpression);
        int start;
        int lastStart = 0;
        int count = 0;
        while(matcher.find()){
            start = matcher.start();
            strings.add(pointCutExpression.substring(lastStart, start));
            //需要保证括号的数量是相对的
            //如果是( count++  如果是 ) count--
            for(int i = start; i < pointCutExpression.length() ; i ++){
                if('(' == pointCutExpression.charAt(i)){
                    count ++;
                }else if(')' == pointCutExpression.charAt(i)){
                    count --;
                    if(count == 0){
                        lastStart = i+1;
                        expressions.add(pointCutExpression.substring(start, i+1));
                        count = 0;
                        break;
                    }
                }
            }
        }
        strings.add(pointCutExpression.substring(lastStart));
    }

}
