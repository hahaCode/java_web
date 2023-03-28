package com.fan.algo;

public class PartitionList {
    public static void main(String[] args) {

    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(), p1 = dummy1;
        ListNode dummy2 = new ListNode(), p2 = dummy2;
        ListNode p = head;

        while (p!=null){
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            p = p.next;
        }
        p2.next = null;
        p1.next = dummy2.next;
        return dummy1.next;
    }
}
