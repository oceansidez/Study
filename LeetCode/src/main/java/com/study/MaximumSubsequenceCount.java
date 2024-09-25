package com.study;

/**
 * @author zzh
 * @date 2024/9/24 17:07
 * https://leetcode.cn/problems/maximize-number-of-subsequences-in-a-string/description/
 * 2207. 字符串中最多数目的子序列
 * 中等
 * 提示
 * 给你一个下标从 0 开始的字符串 text 和另一个下标从 0 开始且长度为 2 的字符串 pattern ，两者都只包含小写英文字母。
 * <p>
 * 你可以在 text 中任意位置插入 一个 字符，这个插入的字符必须是 pattern[0] 或者 pattern[1] 。注意，这个字符可以插入在 text 开头或者结尾的位置。
 * <p>
 * 请你返回插入一个字符后，text 中最多包含多少个等于 pattern 的 子序列 。
 * <p>
 * 子序列 指的是将一个字符串删除若干个字符后（也可以不删除），剩余字符保持原本顺序得到的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：text = "abdcdbc", pattern = "ac"
 * 输出：4
 * 解释：
 * 如果我们在 text[1] 和 text[2] 之间添加 pattern[0] = 'a' ，那么我们得到 "abadcdbc" 。那么 "ac" 作为子序列出现 4 次。
 * 其他得到 4 个 "ac" 子序列的方案还有 "aabdcdbc" 和 "abdacdbc" 。
 * 但是，"abdcadbc" ，"abdccdbc" 和 "abdcdbcc" 这些字符串虽然是可行的插入方案，但是只出现了 3 次 "ac" 子序列，所以不是最优解。
 * 可以证明插入一个字符后，无法得到超过 4 个 "ac" 子序列。
 * 示例 2：
 * <p>
 * 输入：text = "aabb", pattern = "ab"
 * 输出：6
 * 解释：
 * 可以得到 6 个 "ab" 子序列的部分方案为 "aaabb" ，"aaabb" 和 "aabbb" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= text.length <= 105
 * pattern.length == 2
 * text 和 pattern 都只包含小写英文字母。
 */
public class MaximumSubsequenceCount {
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
            // 新的字符串中有多少个p0
            if (c == p0) {
                p0Count++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        MaximumSubsequenceCount ms = new MaximumSubsequenceCount();
        String text = "zigfj";
        String pattern = "ju";
        long l1 = ms.maximumSubsequenceCount(text, pattern);
        System.out.println("l1 = " + l1);
    }
}
