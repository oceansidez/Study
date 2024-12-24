package com.study;

/**
 * @author zzh
 * @date 2024/9/24 17:07
 * https://leetcode.cn/problems/maximize-number-of-subsequences-in-a-string/description/
 * 2207. 字符串中最多数目的子序列
 */
public class Solution2207 {
    // 第一种：一个劲的遍历管他三七二十一直接每种情况都判断
    public long maximumSubsequenceCount(String text, String pattern) {
        // 第一个元素
        char p0 = pattern.charAt(0);
        // 第二个元素
        char p1 = pattern.charAt(1);
        Long res = 0L;
        // 从头到尾依次插入得到每一次的结果，使用<=,插入的个数为字符串个数+1
        for (int i = 0; i <= text.toCharArray().length; i++) {
            // 第一种：使用第一个元素进行插入
            String newText0 = text.substring(0, i) + p0 + text.substring(i);
            // 对第一种情况进行处理拿到个数，并当作最终结果
            Long res0 = handle(newText0, p0, p1);
            // 第二种：使用第第二个元素进行插入
            String newText1 = text.substring(0, i) + p1 + text.substring(i);
            // 对第二种情况进行处理拿到个数
            Long res1 = handle(newText1, p0, p1);
            // 比较得到一次插入不同种的最大个数
            Long resMax = Math.max(res0, res1);
            // 得到全部个数中的最大个数
            res = Math.max(res, resMax);
        }
        return res;
    }

    // 第二种：只有第一个插入到第0个获取，第二个插入到最后一个时候才会得到最大，只用判断两种就行
    public long maximumSubsequenceCount2(String text, String pattern) {
        // 第一个元素
        char p0 = pattern.charAt(0);
        // 第二个元素
        char p1 = pattern.charAt(1);
        Long res = 0L;
        // 第一种：使用第一个元素进行插入
        String newText0 = p0 + text;
        // 对第一种情况进行处理拿到个数，并当作最终结果
        Long res0 = handle(newText0, p0, p1);
        // 第二种：使用第第二个元素进行插入
        String newText1 = text + p1;
        // 对第二种情况进行处理拿到个数
        Long res1 = handle(newText1, p0, p1);
        // 比较得到最大个数
        return Math.max(res0, res1);
    }

    /**
     * 遍历每一种插入后的结果
     *
     * @param newText 新的字符串
     * @param p0
     * @param p1
     * @return
     */
    public Long handle(String newText, char p0, char p1) {
        // 初始化返回的结果
        Long r = 0L;
        Long p0Count = 0L;
        // 遍历并进行匹配
        for (char c : newText.toCharArray()) {
            if (c == p1) {
                r += p0Count;
            }
            // 新的字符串中有多少个p0，需要放在第一个元素后解决 两元素相同问题
            if (c == p0) {
                p0Count++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Solution2207 ms = new Solution2207();
        String text = "zigfj";
        String pattern = "ju";
        long l1 = ms.maximumSubsequenceCount(text, pattern);
        System.out.println("l1 = " + l1);
    }
}
