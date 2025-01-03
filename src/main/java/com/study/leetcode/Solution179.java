package com.study.leetcode;

/**
 * 最大数
 */
public class Solution179 {
    public static String largestNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        // 冒泡
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                String s1 = String.valueOf(nums[i]);
                String s2 = String.valueOf(nums[j]);
                if ((s1 + s2).compareTo(s2 + s1) < 0) {
                    swap(nums, i, j);
                }
            }
        }
        // 排完序第一个还是0那么数组最大都为0直接返回0
        if (nums[0] == 0) {
            return "0";
        }
        for (int num : nums) {
            res.append(num);
        }
        return res.toString();
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1000000000, 1000000000};
        System.out.println("largestNumber(nums) = " + largestNumber(nums));
    }
}