package pers.tom.aop.test;

import pers.tom.aop.aspect.parser.AnnotationAspectDefinitionParser;
import pers.tom.aop.aspect.parser.AspectDefinitionParser;
import pers.tom.aop.proxy.AopProxyFactory;
import pers.tom.aop.proxy.CglibAopProxyFactory;
import pers.tom.aop.proxy.method.factory.MethodProxyFactory;
import pers.tom.aop.proxy.method.factory.TargetMethodProxyFactory;

/**
 * @author tom
 * @description
 * @date 2021/1/7 10:13
 */
public class TestMain {

    public static void main(String[] args) {
        //通过new创建的对象
        Person person = new Person();

        //创建切面信息解析器
        AspectDefinitionParser parser = new AnnotationAspectDefinitionParser("pers/tom/aop/test");

        //创建方法代理工厂
        MethodProxyFactory methodProxyFactory = new TargetMethodProxyFactory(parser);

        //创建对象代理工厂
        AopProxyFactory proxyFactory = new CglibAopProxyFactory(methodProxyFactory);

        Person proxy = (Person) proxyFactory.getProxy(person);

        proxy.eat();
    }
}
