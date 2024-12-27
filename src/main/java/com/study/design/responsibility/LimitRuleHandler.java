package com.study.design.responsibility;

/**
 * @author zzh
 * @date 2024/12/20 14:37
 * @description:
 */
public class LimitRuleHandler extends RuleHandler {

    @Override
    public void apply(Context context) {
        int remainedTimes = 3;
        if (remainedTimes > 0) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("您来得太晚了，奖品被领完了");
        }
    }
}
