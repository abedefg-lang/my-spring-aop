package pers.tom.aop.aspect;

import pers.tom.aop.aspect.advice.Advice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 解析切面的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AspectDefinition {

    /**类对象*/
    private Class<?> clazz;

    /**当前切面的实例对象*/
    private Object instance;

    /**排序顺序*/
    private int order;

    /**当前切面的所有通知*/
    private List<Advice> advices;
}
