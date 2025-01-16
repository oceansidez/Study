package com.study.base.design.responsibility;

/**
 * @author zzh
 * @date 2024/12/20 14:36
 * @description:
 */
public class LocationRuleHandler extends RuleHandler {

    @Override
    public void apply(Context context) {
        boolean allowed = true;
        if (allowed) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("非常抱歉，您所在的地区无法参与本次活动");
        }
    }
}
