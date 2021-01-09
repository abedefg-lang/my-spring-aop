package pers.tom.aop.aspect.parser;

import pers.tom.aop.aspect.AspectDefinition;

import java.util.Comparator;
import java.util.List;

/**
 * 切面信息的解析器  对于不同的配置解析逻辑不同
 * 比如可以使用注解  也可以使用xml文件配置
 */
public interface AspectDefinitionParser {


    Comparator<AspectDefinition> ORDERED_ASPECT_DEFINITION_COMPARATOR = Comparator.comparingInt(AspectDefinition::getOrder);

    /**
     * 解析逻辑
     * @return 返回解析好的信息
     */
    List<AspectDefinition> parse();
}
