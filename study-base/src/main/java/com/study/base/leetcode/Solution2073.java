package com.study.base.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2073. 买票需要的时间
 * https://leetcode.cn/problems/time-needed-to-buy-tickets/description/?envType=daily-question&envId=2024-09-29
 *
 * @author zzh
 * @date 2024/9/29 15:38
 */
public class Solution2073 {

    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;  // 用来记录总共花费的时间
        Queue<Integer> ticketQueue = new LinkedList<>();  // 票数队列
        Queue<Integer> indexQueue = new LinkedList<>();   // 位置队列
        // 初始化队列
        for (int i = 0; i < tickets.length; i++) {
            ticketQueue.offer(tickets[i]);
            indexQueue.offer(i);
        }
        // 模拟买票过程
        while (!ticketQueue.isEmpty()) {
            // 拿到队列中的第一个人
            int ticketsLeft = ticketQueue.poll();  // 当前人剩余的票数
            int personIndex = indexQueue.poll();   // 当前人的位置
            // 购票（减一张票）
            ticketsLeft--;
            // 时间 +1
            res++;
            // 判断这个人是不是第 k 个人，如果是并且票数已经为 0，表示买完票，可以返回结果了
            if (personIndex == k && ticketsLeft == 0) {
                return res;
            }
            // 如果当前人还没有买完票，把他放回队列末尾
            if (ticketsLeft > 0) {
                ticketQueue.offer(ticketsLeft);
                indexQueue.offer(personIndex);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution2073 timeRequiredToBuy = new Solution2073();
        int[] tickets = new int[]{2, 3, 2};
        int k = 2;
        System.out.println("timeRequiredToBuy.timeRequiredToBuy(tickets, k) = " + timeRequiredToBuy.timeRequiredToBuy(tickets, k));

    }
}
