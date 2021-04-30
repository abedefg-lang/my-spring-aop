package pers.tom.aop2.joinpoint;

/**
 * @author lijia
 * @description 可执行加入点
 * @date 2021-04-30 15:39
 */
public interface ProceedingJoinPoint extends JoinPoint{

    /**
     * 执行加入点
     * @return result
     * @throws Throwable throwable
     */
    Object proceed() throws Throwable;

    /**
     * 执行加入点(带参数)
     * @param args args
     * @return result
     * @throws Throwable throwable
     */
    Object proceed(Object[] args) throws Throwable;
}
