package com.study;

/**
 * https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array/description/
 * 2535. 数组元素和与数字和的绝对差
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个正整数数组 nums 。
 * <p>
 * 元素和 是 nums 中的所有元素相加求和。
 * 数字和 是 nums 中每一个元素的每一数位（重复数位需多次求和）相加求和。
 * 返回 元素和 与 数字和 的绝对差。
 * <p>
 * 注意：两个整数 x 和 y 的绝对差定义为 |x - y| 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,15,6,3]
 * 输出：9
 * 解释：
 * nums 的元素和是 1 + 15 + 6 + 3 = 25 。
 * nums 的数字和是 1 + 1 + 5 + 6 + 3 = 16 。
 * 元素和与数字和的绝对差是 |25 - 16| = 9 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 解释：
 * nums 的元素和是 1 + 2 + 3 + 4 = 10 。
 * nums 的数字和是 1 + 2 + 3 + 4 = 10 。
 * 元素和与数字和的绝对差是 |10 - 10| = 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2000
 * 1 <= nums[i] <= 2000
 *
 * @author zzh
 * @date 2024/9/26 10:18
 */
public class DifferenceOfSum {
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
        DifferenceOfSum differenceOfSum = new DifferenceOfSum();
        int[] nums1 = new int[]{1, 15, 6, 3};
        int[] nums2 = new int[]{1, 2, 3, 4};
        System.out.println("differenceOfSum(nums1) = " + differenceOfSum.differenceOfSum(nums1));
        System.out.println("differenceOfSum(nums2) = " + differenceOfSum.differenceOfSum2(nums2));
    }
}
