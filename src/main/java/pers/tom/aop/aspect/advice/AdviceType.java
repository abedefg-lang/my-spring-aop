package pers.tom.aop.aspect.advice;

/**
 * 通知类型
 */
public enum AdviceType {

    /**前置*/
    BEFORE("Before"),

    /**异常*/
    AFTER_THROWING("AfterThrowing"),

    /**返回*/
    AFTER_RETURNING("AfterReturning"),

    /**后置*/
    AFTER("After"),

    /**环绕*/
    AROUND("Around");

    AdviceType(String name){
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }

    /**
     * 通过name获取对应的type
     */
    public static AdviceType nameOf(String name){
        AdviceType[] types = AdviceType.values();
        for(AdviceType type : types){
            if(type.name.equals(name)){
                return type;
            }
        }
        throw new RuntimeException("找不到对应的通知类型" + name);
    }
}
