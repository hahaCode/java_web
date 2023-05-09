package com.fan.algo.LinkedListPorblems;

import java.util.List;

//https://leetcode.cn/problems/intersection-of-two-linked-lists/
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;

//        int count = 0;
//        while (true) {
//            if (p1 != null && p2 != null && p1 == p2) return p1;
//            p1 = p1.next;
//            p2 = p2.next;
//            if (p1 == null) {
//                if (count == 2) return null;
//                p1 = headB;
//                count++;
//            }
//            if (p2 == null) {
//                if (count == 2) return null;
//                p2 = headA;
//                count++;
//            }
//        }
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            }else {
                p1 = p1.next;
            }

            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }

        }
        return p1;
    }
}

