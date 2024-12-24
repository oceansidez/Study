package com.study.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 将第一个作为最小，每次记录比最小的还小的索引进行交换
 */
public class Selection {

    public void selection(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 设置最元素元素为i，代表每轮交换到的目标索引
            int s = i;
            for (int j = s + 1; j < nums.length - 1; j++) {
                if (nums[j] < nums[s]) {
                    // 每次记录最小的索引
                    s = j;
                }
            }
            // 当交换的元素不是自己时才交换
            if (s != i) {
                swap(nums, s, i);
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
        Bubble bubble = new Bubble();
        bubble.bubble(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }
}
