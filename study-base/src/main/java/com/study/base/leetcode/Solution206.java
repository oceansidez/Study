package com.study.base.leetcode;

/**
 * 206.反转链表
 * https://leetcode.cn/problems/reverse-linked-list/description/
 */
public class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode per = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = per;
            per = cur;
            cur = next;
        }
        return per;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode14 = new Solution206().new ListNode(4, null);
        ListNode listNode13 = new Solution206().new ListNode(3, listNode14);
        ListNode listNode12 = new Solution206().new ListNode(2, listNode13);
        ListNode listNode11 = new Solution206().new ListNode(1, listNode12);

        ListNode listNode = new Solution206().reverseList(listNode11);

        while (listNode != null) {
            System.out.println("listNode11 = " + listNode.val);
            listNode = listNode.next;
        }
    }
}
