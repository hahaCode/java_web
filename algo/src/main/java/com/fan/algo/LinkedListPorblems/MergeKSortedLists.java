package com.fan.algo.LinkedListPorblems;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.cn/problems/merge-k-sorted-lists/
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        //最小堆 优先队列
        PriorityQueue<ListNode> priorityQueue =new PriorityQueue<>(lists.length, (a, b)->(a.val-b.val));

        for (ListNode list : lists) {
            if (list != null)
                priorityQueue.add(list);
        }

        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            p.next = node;

            if (node.next != null) {
                priorityQueue.add(node.next);
            }

            p = p.next;
        }
        return dummy.next;
    }
}
