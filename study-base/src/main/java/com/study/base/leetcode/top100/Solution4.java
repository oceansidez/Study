package com.study.base.leetcode.top100;

/**
 * 寻找两个正序数组的中位数
 * 解法：暴力，方法二待写
 */
public class Solution4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 合并数组
        int[] res = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            res[nums1.length + i] = nums2[i];
        }

        // 排序
        for (int i = 0; i < res.length; i++) {
            for (int j = i + 1; j < res.length; j++) {
                if (res[i] > res[j]) {
                    int temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }

        int l = 0, r = res.length - 1;
        int m = (l + r) >>> 1;
        // 取中间的数字
        if (res.length % 2 == 0) {
            return (res[m] + res[m + 1]) / 2.0;
        } else {
            return res[m];
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println("medianSortedArrays = " + medianSortedArrays);
    }
}
