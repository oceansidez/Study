package com.study.base.leetcode.top100;

/**
 * https://leetcode.cn/problems/climbing-stairs/description/?envType=problem-list-v2&envId=2cktkvj
 * 70. 爬楼梯
 * 解法：动态规划
 */
public class Solution70 {

    public static int climbStairs(int n) {
        int pre = 1;
        int next = 1;
        for (int i = 2; i <= n; i++) {
            int temp = next;
            next = pre + next;
            pre = temp;
        }
        return next;
    }

    static int climbStairs2(int n) {
        //因为有n阶台阶，台阶从0开始计算，所以定义n+1个元素
        int[] dp = new int[n + 1];
        //定义边界dp[0]=1
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            //状态转移公式
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        int n = 4;
        int res = climbStairs2(n);
        System.out.println("res = " + res);
    }
}
