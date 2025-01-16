package com.study.base.leetcode.top100;

/**
 * 寻找两个正序数组的中位数
 * 解法：双指针
 */
public class Solution5 {
    public static String longestPalindrome(String s) {
        int resLen = 0;
        int resStart = 0;
        for (int i = 0; i < s.length(); i++) {
            //若回文串长度为奇数,以s[i]为中心向两边扩散寻找以s[i]为中心的最长回文子串
            int l = i, r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > resLen) {
                    resLen = r - l + 1;
                    resStart = l;
                }
                l--;
                r++;
            }
            //若回文串长度为偶数，以s[i]s[i+1]为中心向两边扩散寻找以s[i]s[i+1]为中心的最长回文子串
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > resLen) {
                    resLen = r - l + 1;
                    resStart = l;
                }
                l--;
                r++;
            }
        }
        return s.substring(resStart, resLen + resStart);
    }


    public static void main(String[] args) {
        String s = "cbbd";
        String res = longestPalindrome(s);
        System.out.println("res = " + res);
    }
}
