package com.study.sort;

import java.util.Arrays;

/**
 * 快排
 */
public class Quick {

    public void quick(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition2(nums, l, r);
        // 左边分区
        quick(nums, l, p - 1);
        // 右边分区
        quick(nums, p + 1, r);
    }

    /**
     * 单边循环
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int partition1(int[] nums, int l, int r) {
        // 基准元素
        int pv = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] < pv) {
                swap(nums, i, j);
                i++;
            }
        }
        // 将基准点与i交换
        swap(nums, i, r);
        return i;
    }


    /**
     * 双边循环
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int partition2(int[] nums, int l, int r) {
        int pv = nums[l];
        int i = l;
        int j = r;
        while (i < j) {
            // j 从右找小（必须要先执行）
            while (i < j && nums[j] > pv) {
                j--;
            }
            //  i 从左找大,防止将基准点进行交换(nums[i] <= pv),防止i右移动的时候找到的值一直小于pv直到超过了j都还小于（i < j）
            while (i < j && nums[i] <= pv) {
                i++;
            }
            // 相交后将基准点元素与j交换
            swap(nums, i, j);
        }
        swap(nums, j, l);
        return j;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 4, 6, 3, 9};
//        int[] nums = {2, 3, 4, 5, 1};
        Quick quick = new Quick();
        quick.quick(nums, 0, nums.length - 1);
        System.out.println("nums = " + Arrays.toString(nums));
    }
}
