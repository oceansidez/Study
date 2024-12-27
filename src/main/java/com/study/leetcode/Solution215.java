package com.study.leetcode;

import java.util.Arrays;

/**
 * 215. 数组中的第K个最大元素
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 */
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        quicker(nums);
        return nums[k - 1];
    }

    /**
     * 快排
     *
     * @param nums
     */
    public void quicker(int[] nums) {
        int q = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < q) {

            }
        }
    }


    public static void main(String[] args) {
        Solution215 solution = new Solution215();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        solution.findKthLargest(nums, 4);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}