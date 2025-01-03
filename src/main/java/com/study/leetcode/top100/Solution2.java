package com.study.leetcode.top100;

/**
 * 两数相加
 * 解法：模拟数字相加，只要需要临时变量指向链表，如果最后有进位则需要创建一个节点1
 */
public class Solution2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode();
        ListNode head = temp;
        // 进位
        int c = 0;
        while (l1 != null || l2 != null) {
            // 如果有一端没有那么默认为0
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2;
            // 头节点不会有进位
            if (head == null) {
                temp = new ListNode(sum);
            } else {
                // 当进位相加后为10那么需要再次区域并将sum设置为10 如 9 + 99
                int i = sum % 10 + c;
                if (i == 10) {
                    sum = i;
                    i = i % 10;
                }
                temp.next = new ListNode(i);
            }
            temp = temp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            c = sum / 10;
        }
        if (c > 0) {
            temp.next = new ListNode(1);
        }
        return head.next;
    }


    public static void main(String[] args) {
        ListNode l14 = new ListNode(3, null);
        ListNode l13 = new ListNode(3, l14);
        ListNode l12 = new ListNode(4, l13);
        ListNode l1 = new ListNode(2, l12);

        ListNode l23 = new ListNode(4, null);
        ListNode l22 = new ListNode(6, l23);
        ListNode l2 = new ListNode(5, l22);

        ListNode listNode = addTwoNumbers(l1, l2);

        System.out.println("listNode = " + listNode);
    }
}
