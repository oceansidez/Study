package com.study.base.leetcode.top100;

import java.util.HashSet;

/**
 * 无重复字符的最长子串
 * 解法：滑动窗口
 */
public class Solution3 {
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int l = 0, r = 0;
        int max = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            while (l <= r && set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }


    public static void main(String[] args) {
        // aab
        System.out.println("solution.lengthOfLongestSubstring(\"pwwkew\") = " + lengthOfLongestSubstring("dvdf"));
//        System.out.println("solution.lengthOfLongestSubstring2(\"pwwkew\") = " + lengthOfLongestSubstring("pwwkew"));
//        System.out.println("solution.lengthOfLongestSubstring2(\"pwwkew\") = " + lengthOfLongestSubstring("abcabcbb"));
    }
}
