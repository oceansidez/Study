package com.study.leetcode;

/**
 * https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array/description/
 * 2535. 数组元素和与数字和的绝对差
 *
 * @author zzh
 * @date 2024/9/26 10:18
 */
public class Solution2352 {
    // 第一种 分割字符串方式
    public int differenceOfSum(int[] nums) {
        // 元素和
        int elementSum = 0;
        // 数字和
        int numberSum = 0;
        for (int num : nums) {
            elementSum += num;
            for (String s : String.valueOf(num).split("")) {
                numberSum += Integer.parseInt(s);
            }
        }
        return Math.abs(elementSum - numberSum);
    }

    // 第二种 使用取余的方式，时间最短
    public int differenceOfSum2(int[] nums) {
        // 元素和
        int elementSum = 0;
        // 数字和
        int numberSum = 0;
        for (int num : nums) {
            elementSum += num;
            // 0%10 = 0 作为结束条件
            while (num > 0) {
                // 拿到个位
                int pos = num % 10;
                numberSum += pos;
                // 去掉个位
                num /= 10;
            }
        }
        return Math.abs(elementSum - numberSum);
    }

    public static void main(String[] args) {
        Solution2352 differenceOfSum = new Solution2352();
        int[] nums1 = new int[]{1, 15, 6, 3};
        int[] nums2 = new int[]{1, 2, 3, 4};
        System.out.println("differenceOfSum(nums1) = " + differenceOfSum.differenceOfSum(nums1));
        System.out.println("differenceOfSum(nums2) = " + differenceOfSum.differenceOfSum2(nums2));
    }
}
