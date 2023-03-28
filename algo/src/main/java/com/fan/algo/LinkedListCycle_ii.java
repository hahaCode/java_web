package com.fan.algo;

public class LinkedListCycle_ii {

    public static void main(String[] args) {

    }
    public ListNode detectCycle(ListNode head) {
// Time Out
        if (head == null || head.next == null || head.next.next == null) return null;

        boolean flag = false;
        ListNode slow = head, fast = head.next;
        while (slow != null && fast!= null) {

            if (fast.next == null) break;

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                flag = true;
                break;
            }
        }
        if (flag == false) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
