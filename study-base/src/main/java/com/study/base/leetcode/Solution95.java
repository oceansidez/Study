package com.study.base.leetcode;

/**
 * https://leetcode.cn/problems/qJnOS7/description/
 * 1905. 最长公共子序列
 * 解法：动态规划，分末尾相同还是不同，在一次判断除了匹配之后的其余的情况
 */
public class Solution95 {
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        String t1 = "abcde";
        String t2 = "ace";
        int i = longestCommonSubsequence(t1, t2);
        System.out.println("i = " + i);
    }
}
