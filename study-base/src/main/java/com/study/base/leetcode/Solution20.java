package com.study.base.leetcode;

import java.util.Stack;

/**
 * 20.有效的括号
 * https://leetcode.cn/problems/valid-parentheses/description/
 */
class Solution20 {
    public boolean isValid(String s) {
        String[] chars = s.split("");
        Stack<String> characters = new Stack<>();
        try {
            for (String c : chars) {
                boolean b = false;
                if (c.equals("(") || c.equals("[") || c.equals("{")) {
                    characters.push(c);
                    b = true;
                } else if (c.equals(")") && characters.peek().equals("(")) {
                    characters.pop();
                    b = true;
                } else if (c.equals("]") && characters.peek().equals("[")) {
                    characters.pop();
                    b = true;
                } else if (c.equals("}") && characters.peek().equals("{")) {
                    characters.pop();
                    b = true;
                }
                if (!b) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        if (characters.empty()) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Solution20 solution = new Solution20();
        System.out.println("solution.isValid(\"()[]{}\") = " + solution.isValid("(])"));
    }
}