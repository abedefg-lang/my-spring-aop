package pers.tom.aop.utils.reflect;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectUtils {


    /**
     * 获取一个类型所有的属性  包括父类的私有属性
     */
    public static Field[] getAllField(Class<?> type){
        List<Field> fieldList = new ArrayList<>();
        Class<?> clazz = type;
        //当这个类型是Object的时候停止循环
        while(clazz != null){
            Field[] fields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        return fieldList.toArray(new Field[0]);
    }

    /**
     * 执行指定方法
     */
    public static Object invokeMethod(Object object, String methodName, Object... args){
        Class<?>[] parameterTypes = null;
        if(args != null){
            int len = args.length;
            parameterTypes = new Class<?>[len];
            for(int i = 0 ; i < len ; i ++){
                parameterTypes[i] = args.getClass();
            }
        }
        Object result = null;
        try{
            Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
            result = method.invoke(object, args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取默认的classLoader 也就是当前线程的classLoader
     */
    public static ClassLoader getDefaultClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }


}
