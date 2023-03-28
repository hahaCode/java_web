package com.fan.algo;

public class LinkedListCycle_ii {

    public static void main(String[] args) {

    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast!= null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }

        if (fast!= null || fast.next!=null) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
