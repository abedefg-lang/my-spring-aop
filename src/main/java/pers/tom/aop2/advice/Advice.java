package pers.tom.aop2.advice;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * @author lijia
 * @description 通知
 * @date 2021-04-26 11:41
 */
public interface Advice {

    /**通知比较器*/
    Comparator<Advice> ADVICE_COMPARATOR = Comparator.comparingInt(Advice::getOrder);

    /**
     * 判断当前通知是否能够织入指定方法
     * @param method method
     * @return boolean
     */
    boolean match(Method method);

    /**
     * 获取执行顺序
     * @return int
     */
    int getOrder();
}
