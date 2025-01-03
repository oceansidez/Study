package com.study.leetcode.top100;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-break/description/?envType=problem-list-v2&envId=2cktkvj
 * 139.单词拆分
 * 解法：动态规划
 */
public class Solution139 {

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
        boolean res = wordBreak(s, wordDict);
        System.out.println("res = " + res);
    }
}
