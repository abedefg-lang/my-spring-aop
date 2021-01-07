package aop.aspect.point_cut;


import aop.aspect.matcher.PointCutMatcher;
import aop.aspect.matcher.PointCutMatcherRegistry;
import aop.utils.PointCutExpressionUtils;
import lombok.Getter;
import lombok.Setter;
import aop.utils.string.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * 在配置切点表达式的时候 一个表达式可能会配置多种规则
 * 比如execution(...) && args(...) || annotation(...) ...
 * 这样一个表达式就需要多个匹配器进行校验
 * 实现方式是先将上面的表达式转换成boolean的字符串表达式比如(true && false) || false ...
 * 然后再处理这个boolean的字符串
 */
@Getter
@Setter
public class PointCutExpression{

    /**切点表达式*/
    private String pointCutExpression;

    /**顺序解析出来的匹配器*/
    private List<PointCutMatcher> matcherList;

    /**一一对应上面匹配器需要用到的表达式*/
    private List<String> expressions;

    /**连接字符  || && !等*/
    private List<String> strings;

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

    private PointCutExpression(){}

    public boolean match(Method method){
        Objects.requireNonNull(method, "method不能为null");
        StringBuilder builder = new StringBuilder();
        int index = 0;
        String exr;
        while(index < matcherList.size()){
            exr = expressions.get(index);
            builder.append(strings.get(index))
                    .append(matcherList.get(index).match(method, exr));
            index ++;
        }
        if(strings.size() > expressions.size()){
            builder.append(strings.get(index));
        }
        return StringUtils.parseBooleanExpression(builder.toString());
    }


    public static PointCutExpression toPointCutExpression(String pointCutExpression){
        //首先简单检查表达式
        PointCutExpressionUtils.simpleValidation(pointCutExpression);
        List<String> expressions = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        //解析
        PointCutExpressionUtils.parseSimpleExpression(pointCutExpression, expressions, strings);
        List<String> exps = new ArrayList<>(expressions.size());
        List<PointCutMatcher> matcherList = new ArrayList<>();
        for(String exp : expressions){
            int idx1 = exp.indexOf("(");
            int idx2 = exp.lastIndexOf(")");
            String rule = exp.substring(0, idx1).trim();
            String content = exp.substring(idx1+1, idx2).trim();
            PointCutMatcher matcher = PointCutMatcherRegistry.getMatcher(rule);
            if(matcher == null){
                throw new RuntimeException("找不到" + rule + "对应的匹配器");
            }
            exps.add(content);
            matcherList.add(matcher);
        }
        PointCutExpression pce = new PointCutExpression();
        pce.expressions = exps;
        pce.matcherList = matcherList;
        pce.strings = strings;
        pce.pointCutExpression = pointCutExpression;
        return pce;
    }

}
