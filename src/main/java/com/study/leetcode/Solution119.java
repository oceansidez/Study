package com.study.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/WhsWhI/description/
 * 119. 最长连续序列
 */
public class Solution119 {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        Set<Integer> setNums = new LinkedHashSet<>();
        for (int num : nums) {
            setNums.add(num);
        }
        nums = new int[setNums.size()];
        Iterator<Integer> iterator1 = setNums.iterator();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = iterator1.next();
        }
        int count = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i] - 1) {
                count++;
            } else {
                count = 1;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    public static int longestConsecutive2(int[] nums) {
        Set<Integer> numSet = new LinkedHashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int res = 0;
        for (int num : nums) {
            // 比当前连续小的跳过，不连续的进入while
            if (!numSet.contains(num - 1)) {
                int curr = num;
                int currentStreak = 1;
                // 找当前连续大的
                while (numSet.contains(curr + 1)) {
                    curr++;
                    currentStreak++;
                }
                res = Math.max(res, currentStreak);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3};
        int i = longestConsecutive2(nums);
        System.out.println("i = " + i);

    }
}
