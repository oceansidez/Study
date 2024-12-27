package com.study.design.responsibility;

/**
 * @author zzh
 * @date 2024/12/20 14:34
 * @description:
 */
public class NewUserRuleHandler extends RuleHandler {

    @Override
    public void apply(Context context) {
        if (context.isNewUser()) {
            // 如果有后继节点的话，传递下去
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("该活动仅限新用户参与");
        }
    }
}
