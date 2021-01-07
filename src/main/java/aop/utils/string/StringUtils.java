package aop.utils.string;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

public class StringUtils {

    /**
     * 解析Boolean表达式的字符串
     */
    public static boolean parseBooleanExpression(String be){
        JexlBuilder jexlBuilder = new JexlBuilder();
        JexlEngine jexl = jexlBuilder.create();
        JexlExpression jexlExpression = jexl.createExpression(be);
        MapContext jexlContext = new MapContext();
        return (boolean) jexlExpression.evaluate(jexlContext);
    }
}
