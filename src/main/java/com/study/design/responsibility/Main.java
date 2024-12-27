package com.study.design.responsibility;

/**
 * @author zzh
 * @date 2024/12/20 16:08
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        RuleHandler newUserHandler = new NewUserRuleHandler();
        RuleHandler locationHandler = new LocationRuleHandler();
        RuleHandler limitHandler = new LimitRuleHandler();
        //是否新用户
        newUserHandler.setSuccessor(locationHandler);
        // 假设本次活动仅校验地区和奖品数量，不校验新老用户
        locationHandler.setSuccessor(limitHandler);

        newUserHandler.apply(context);
        System.out.println("通过");
    }
}
