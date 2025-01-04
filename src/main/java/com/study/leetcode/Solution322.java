package com.study.leetcode;

/**
 * https://leetcode.cn/problems/coin-change/description/
 * 322. 零钱兑换
 * 解法：动态规划
 */
public class Solution322 {

    public static int coinChange(int[] coins, int amount) {
        //因为dp是从0开始，要兑换总金额为amount，所以要申请amount+1个元素
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        //边界处理
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                //剪掉节点为负的情况
                if (i - coins[j] >= 0) {
                    //状态转移公式
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("res = " + coinChange(coins, amount));
    }
}
