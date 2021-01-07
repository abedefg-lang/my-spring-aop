package test;

import aop.aspect.parser.AnnotationAspectDefinitionParser;
import aop.aspect.parser.AspectDefinitionParser;
import aop.proxy.AopProxyFactory;
import aop.proxy.CglibAopProxyFactory;
import aop.proxy.method.factory.MethodProxyFactory;
import aop.proxy.method.factory.TargetMethodProxyFactory;

/**
 * @author lijia
 * @description
 * @date 2021/1/7 10:13
 */
public class TestMain {

    public static void main(String[] args) {
        //通过new创建的对象
        Person person = new Person();

        //创建切面信息解析器
        AspectDefinitionParser parser = new AnnotationAspectDefinitionParser("test");

        //创建方法代理工厂
        MethodProxyFactory methodProxyFactory = new TargetMethodProxyFactory(parser);

        //创建对象代理工厂
        AopProxyFactory proxyFactory = new CglibAopProxyFactory(methodProxyFactory);

        Person proxy = (Person) proxyFactory.getProxy(person);

        proxy.eat();
    }
}
