package pers.tom.aop.aspect.parser;

import com.tom.aop.annotation.*;
import pers.tom.aop.annotation.*;
import pers.tom.aop.aspect.advice.Advice;
import pers.tom.aop.aspect.AspectDefinition;
import pers.tom.aop.aspect.point_cut.PointCutExpression;
import pers.tom.aop.aspect.advice.AdviceType;
import org.reflections.Reflections;
import pers.tom.aop.utils.reflect.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 解析基于注解配置的切面
 */
public class AnnotationAspectDefinitionParser implements AspectDefinitionParser {

    /**需要解析的注解类型*/
    private static final Set<Class<? extends Annotation>> ANNOTATION_SET;

    /**包路径*/
    private final String basesPackage;

    /**切点表达式的缓存 */
    private final Map<String, PointCutExpression> pointCutCache = new HashMap<>();

    static {
        ANNOTATION_SET = new HashSet<>();
        ANNOTATION_SET.add(Before.class);
        ANNOTATION_SET.add(AfterThrowing.class);
        ANNOTATION_SET.add(AfterReturning.class);
        ANNOTATION_SET.add(After.class);
        ANNOTATION_SET.add(Around.class);
    }

    public AnnotationAspectDefinitionParser(String basePackage){
        this.basesPackage = basePackage;
    }

    @Override
    public List<AspectDefinition> parse() {
        //扫包 找到所有类上面标注了Aspect注解的Class
        Reflections reflections = new Reflections(basesPackage);
        Set<Class<?>> aspectClasses = reflections.getTypesAnnotatedWith(Aspect.class);
        //解析PointCut注解
        parsePointCut(aspectClasses);
        List<AspectDefinition> list = new ArrayList<>();

        Aspect aspect;
        Object instance;
        for(Class<?> clazz : aspectClasses){
            aspect = clazz.getDeclaredAnnotation(Aspect.class);
            try{
                instance = clazz.newInstance();
                //创建 并添加
                list.add(new AspectDefinition(clazz, instance, aspect.order(), parseAdvice(clazz, instance)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        list.sort(ORDERED_ASPECT_DEFINITION_COMPARATOR);
        return list;
    }

    /**
     * 解析通知
     */
    private List<Advice> parseAdvice(Class<?> clazz, Object instance){
        //获取所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        List<Advice> list = new ArrayList<>();
        Annotation[] annotations;
        Advice advice;
        for(Method method : methods){
            annotations = method.getDeclaredAnnotations();
            //循环数组进行判断是否有包含需要解析的注解
            for(Annotation annotation : annotations){
                //判断是否包含需要解析的注解
                if(ANNOTATION_SET.contains(annotation.annotationType())){
                    //通过注解名字获取对应的通知类型
                    AdviceType type = AdviceType.nameOf(annotation.annotationType().getSimpleName());
                    //获取配置的切点字符串
                    String pceStr = ((String) ReflectUtils.invokeMethod(annotation, "value")).trim();
                    //创建通知对象
                    advice = new Advice(method, instance);
                    advice.setPointCutExpression(getPointCutExpression(pceStr, clazz.getName()));
                    advice.setAdviceType(type);
                    list.add(advice);
                }
            }
        }
        return list;
    }

    /**
     * 解析 pointCut注解
     */
    private void parsePointCut(Set<Class<?>> classes){
        for(Class<?> clazz : classes){
            Method[] methods = clazz.getDeclaredMethods();
            String key;
            for(Method m : methods){
                PointCut pointCut = m.getDeclaredAnnotation(PointCut.class);
                if(pointCut != null){
                    key = m.getDeclaringClass().getName()+"."+m.getName();
                    pointCutCache.put(key, PointCutExpression.toPointCutExpression(pointCut.value()));
                }
            }
        }
    }

    private PointCutExpression getPointCutExpression(String expression, String className){
        //直接从缓存中取
        PointCutExpression pce = pointCutCache.get(expression);
        if(pce == null){
            //如果缓存中没有 有可能使用的短名  拼接成全名再次获取
            pce = pointCutCache.get(className+"."+expression);
            if(pce == null){
                //如果还是没有再进行创建
                pce = PointCutExpression.toPointCutExpression(expression);
            }
        }
        return pce;
    }

}
