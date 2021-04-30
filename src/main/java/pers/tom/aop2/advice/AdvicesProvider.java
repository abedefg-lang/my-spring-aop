package pers.tom.aop2.advice;

import java.util.List;

/**
 * @author lijia
 * @description 通知提供方
 * @date 2021-04-30 15:56
 */
public interface AdvicesProvider {

    /**
     * 获取通知
     * @return advices
     */
    List<Advice> getAdvices();
}
