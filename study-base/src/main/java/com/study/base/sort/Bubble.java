package com.study.base.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 每次交换相邻的
 */
public class Bubble {

    public void bubble(int[] nums) {
        // 轮数
        for (int i = 0; i < nums.length - 1; i++) {
            // 交换次数
            // 全程一论是否发生交换，未进行交换直接返回
            boolean isSwap = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 4, 6, 3, 9};
        Selection selection = new Selection();
        selection.selection(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }
}
