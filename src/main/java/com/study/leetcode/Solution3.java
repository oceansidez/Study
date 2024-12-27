package com.study.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 有效的括号
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 */
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0 || length == 1) {
            return length;
        }
        int res = 1;
        int l = 0;
        int r = 1;
        char[] chars = s.toCharArray();
        while (r < length) {
            int computeSubstring = computeSubstring(chars, l, r);
            if (computeSubstring == -1) {
                l++;
            }
            r++;
            res = Math.max(res, computeSubstring);
        }
        return res;
    }


    /**
     * 计算字串长度
     */
    public int computeSubstring(char[] chars, int l, int r) {
        int compute = 0;
        Map<Character, Integer> strMap = new HashMap<>();
        for (int i = l; i <= r; i++) {
            if (strMap.get(chars[i]) == null) {
                strMap.put(chars[i], 1);
                compute++;
            } else {
                return -1;
            }
        }
        return compute;
    }

    public int lengthOfLongestSubstring2(String s) {
        //滑动窗口
        char[] ss = s.toCharArray();
        Set<Character> set = new HashSet<>();//去重
        int res = 0;//结果
        int l = 0;
        for (int r = 0; r < ss.length; r++) {
            while (set.contains(ss[r])) {
                set.remove(ss[l]);
                l++;
            }
            set.add(ss[r]);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        // aab
        Solution3 solution = new Solution3();
        System.out.println("solution.lengthOfLongestSubstring(\"pwwkew\") = " + solution.lengthOfLongestSubstring("dvdf"));
        System.out.println("solution.lengthOfLongestSubstring2(\"pwwkew\") = " + solution.lengthOfLongestSubstring2("pwwkew"));
    }
}