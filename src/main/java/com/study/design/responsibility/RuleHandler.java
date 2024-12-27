package com.study.design.responsibility;

/**
 * @author zzh
 * @date 2024/12/20 14:32
 * @description:
 */
public abstract class RuleHandler {
    // 下一个节点
    protected RuleHandler successor;

    public abstract void apply(Context context);

    public RuleHandler getSuccessor() {
        return successor;
    }

    public void setSuccessor(RuleHandler successor) {
        this.successor = successor;
    }
}
