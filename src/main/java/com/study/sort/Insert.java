package com.study.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insert {

    public void insert(int[] nums) {
        // i 代表待插入元素索引
        for (int i = 1; i < nums.length; i++) {
            int t = nums[i];
            int j = i - 1;
            while (j >= 0) {
                if (t < nums[j]) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
                j--;
            }
            nums[j + 1] = t;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 4, 6, 3, 9};
        Insert insert = new Insert();
        insert.insert(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }
}
