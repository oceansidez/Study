package com.study.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/find-bottom-left-tree-value/description/
 * 513. 找树左下角的值
 * 解法：广度优先 bfs，深度出不来 dfs
 */
public class Solution513 {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root;
        while (queue.size() > 0) {
            TreeNode peek = queue.peek();
            last = peek;
            queue.poll();
            if (peek.right != null) {
                queue.offer(peek.right);
            }
            if (peek.left != null) {
                queue.offer(peek.left);
            }
        }
        return last.val;
    }


    public static void main(String[] args) {

    }
}
