package com.study.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MaxSubsequenceWithDetails {

    // 记录插入位置、字符、子序列数量以及子序列坐标的类
    static class InsertionResult {
        int position;  // 插入位置
        char insertedChar;  // 插入的字符
        int subsequenceCount;  // 子序列数量
        List<List<Integer>> subsequencePositions;  // 每个子序列的坐标

        public InsertionResult(int position, char insertedChar, int subsequenceCount, List<List<Integer>> subsequencePositions) {
            this.position = position;
            this.insertedChar = insertedChar;
            this.subsequenceCount = subsequenceCount;
            this.subsequencePositions = subsequencePositions;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("插入 '").append(insertedChar).append("' 在位置 ").append(position).append(" => ");
            sb.append(subsequenceCount).append(" 子序列的位置为: ").append(subsequencePositions);
            return sb.toString();
        }
    }

    // 计算原始 text 中 pattern 的子序列数量，并返回每个子序列的坐标
    private static InsertionResult countPatternSubsequencesWithPositions(String text, int insertPosition, char insertedChar, char p0, char p1) {
        int countP0 = 0;
        int subseqCount = 0;
        List<Integer> p0Positions = new ArrayList<>();
        List<List<Integer>> subsequencePositions = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == p1) {
                // 当遇到 p1 时，将所有之前遇到的 p0 位置和当前 p1 组成子序列
                for (int p0Pos : p0Positions) {
                    List<Integer> positionPair = new ArrayList<>();
                    positionPair.add(p0Pos);
                    positionPair.add(i);
                    subsequencePositions.add(positionPair);
                }
                subseqCount += countP0;
            }
            if (c == p0) {
                countP0++;
                p0Positions.add(i);  // 记录每个 p0 的位置
            }
        }

        // 返回插入后的结果
        return new InsertionResult(insertPosition, insertedChar, subseqCount, subsequencePositions);
    }

    // 返回插入每个字符后 pattern 的子序列数量及位置
    public static List<InsertionResult> getAllInsertionResults(String text, String pattern) {
        char p0 = pattern.charAt(0);
        char p1 = pattern.charAt(1);
        List<InsertionResult> results = new ArrayList<>();

        // 遍历每个插入位置，插入 p0 或 p1
        for (int i = 0; i <= text.length(); i++) {
            // 插入 p0
            String newTextWithP0 = text.substring(0, i) + p0 + text.substring(i);
            InsertionResult resultP0 = countPatternSubsequencesWithPositions(newTextWithP0, i, p0, p0, p1);
            results.add(resultP0);

            // 插入 p1
            String newTextWithP1 = text.substring(0, i) + p1 + text.substring(i);
            InsertionResult resultP1 = countPatternSubsequencesWithPositions(newTextWithP1, i, p1, p0, p1);
            results.add(resultP1);
        }

        return results;
    }

    public static void main(String[] args) {
        String text = "aabb";
        String pattern = "ab";

        List<InsertionResult> results = getAllInsertionResults(text, pattern);
        for (InsertionResult result : results) {
            System.out.println(result);
        }
    }
}
