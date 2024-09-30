package com.study;

import java.util.*;

/**
 * 2073. 买票需要的时间
 * https://leetcode.cn/problems/time-needed-to-buy-tickets/description/?envType=daily-question&envId=2024-09-29
 * <p>
 * 有 n 个人前来排队买票，其中第 0 人站在队伍 最前方 ，第 (n - 1) 人站在队伍 最后方 。
 * <p>
 * 给你一个下标从 0 开始的整数数组 tickets ，数组长度为 n ，其中第 i 人想要购买的票数为 tickets[i] 。
 * <p>
 * 每个人买票都需要用掉 恰好 1 秒 。一个人 一次只能买一张票 ，如果需要购买更多票，他必须走到  队尾 重新排队（瞬间 发生，不计时间）。如果一个人没有剩下需要买的票，那他将会 离开 队伍。
 * <p>
 * 返回位于位置 k（下标从 0 开始）的人完成买票需要的时间（以秒为单位）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tickets = [2,3,2], k = 2
 * <p>
 * 输出：6
 * <p>
 * 解释：
 * <p>
 * 队伍一开始为 [2,3,2]，第 k 个人以下划线标识。
 * 在最前面的人买完票后，队伍在第 1 秒变成 [3,2,1]。
 * 继续这个过程，队伍在第 2 秒变为[2,1,2]。
 * 继续这个过程，队伍在第 3 秒变为[1,2,1]。
 * 继续这个过程，队伍在第 4 秒变为[2,1]。
 * 继续这个过程，队伍在第 5 秒变为[1,1]。
 * 继续这个过程，队伍在第 6 秒变为[1]。第 k 个人完成买票，所以返回 6。
 * 示例 2：
 * <p>
 * 输入：tickets = [5,1,1,1], k = 0
 * <p>
 * 输出：8
 * <p>
 * 解释：
 * <p>
 * 队伍一开始为 [5,1,1,1]，第 k 个人以下划线标识。
 * 在最前面的人买完票后，队伍在第 1 秒变成 [1,1,1,4]。
 * 继续这个过程 3 秒，队伍在第 4 秒变为[4]。
 * 继续这个过程 4 秒，队伍在第 8 秒变为[]。第 k 个人完成买票，所以返回 8。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == tickets.length
 * 1 <= n <= 100
 * 1 <= tickets[i] <= 100
 * 0 <= k < n
 *
 * @author zzh
 * @date 2024/9/29 15:38
 */
public class TimeRequiredToBuy {

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
        TimeRequiredToBuy timeRequiredToBuy = new TimeRequiredToBuy();
        int[] tickets = new int[]{2, 3, 2};
        int k = 2;
        System.out.println("timeRequiredToBuy.timeRequiredToBuy(tickets, k) = " + timeRequiredToBuy.timeRequiredToBuy(tickets, k));

    }
}
