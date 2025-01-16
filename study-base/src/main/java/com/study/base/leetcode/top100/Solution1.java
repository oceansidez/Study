package com.study.base.leetcode.top100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * 解法：将值和对应的下标记录到map，查找值和当前值的状态
 */
public class Solution1 {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));
    }
}
