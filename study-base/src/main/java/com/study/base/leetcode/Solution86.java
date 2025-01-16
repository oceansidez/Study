package com.study.base.leetcode;


/**
 * 86.分隔链表
 * https://leetcode.cn/problems/partition-list/description/
 */
public class Solution86 {
    public ListNode partition(ListNode head, int x) {
        ListNode s = new ListNode();
        ListNode l = new ListNode();
        // 指针
        ListNode stemp = s;
        ListNode ltemp = l;
        while (head != null) {
            if (head.val < x) {
                stemp.next = head;
                stemp = stemp.next;
            } else {
                ltemp.next = head;
                ltemp = ltemp.next;
            }
            head = head.next;
        }
        ltemp.next = null;
        stemp.next = l.next;
        return s.next;
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
        ListNode listNode16 = new Solution86().new ListNode(2, null);
        ListNode listNode15 = new Solution86().new ListNode(5, listNode16);
        ListNode listNode14 = new Solution86().new ListNode(2, listNode15);

        ListNode listNode13 = new Solution86().new ListNode(3, listNode14);
        ListNode listNode12 = new Solution86().new ListNode(4, listNode13);
        ListNode listNode11 = new Solution86().new ListNode(1, listNode12);

        listNode11 = new Solution86().partition(listNode11, 3);

        while (true) {
            System.out.println("listNode21 = " + listNode11.val);
            if (listNode11.next == null) {
                break;
            }
            listNode11 = listNode11.next;
        }
    }
}
