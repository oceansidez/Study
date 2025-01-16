package com.study.base.leetcode;


/**
 * 21.合并两个有序链表
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 */
public class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode();
        ListNode temp = listNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        temp.next = list1 == null ? list2 : list1;
        return listNode.next;
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

    // 1 1 2 3 4 4
    public static void main(String[] args) {
        ListNode listNode13 = new Solution21().new ListNode(4, null);
        ListNode listNode12 = new Solution21().new ListNode(2, listNode13);
        ListNode listNode11 = new Solution21().new ListNode(1, listNode12);

        ListNode listNode23 = new Solution21().new ListNode(4, null);
        ListNode listNode22 = new Solution21().new ListNode(3, listNode23);
        ListNode listNode21 = new Solution21().new ListNode(1, listNode22);

        listNode21 = new Solution21().mergeTwoLists(listNode11, listNode21);

        while (true) {
            System.out.println("listNode21 = " + listNode21.val);
            if (listNode21.next == null) {
                break;
            }
            listNode21 = listNode21.next;
        }
    }
}
