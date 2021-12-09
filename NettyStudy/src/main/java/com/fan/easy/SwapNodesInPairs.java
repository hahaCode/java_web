package com.fan.easy;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        SNIP_Solution1 solution1 = new SNIP_Solution1();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode head = solution1.swapPairs(node1);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class SNIP_Solution1 {
    /**
     *
     * @param head  就是第一个数据节点
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode cur, pre;

        // 如果没有节点或者只有一个节点那么返回head
        if (head == null || head.next == null){
            return head;
        } else {  // 节点个数大于等于2
            cur = head;
            pre = null;
            head = head.next;  // 最后交换之后肯定是 原来的第二个节点是头节点
        }

        while (cur != null && cur.next != null) {  // 当前没有交换的节点中 满足成对出现的情况
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = cur;
            if(pre != null)
                pre.next = temp;

            pre = cur;
            cur = cur.next;

        }

        return head;
    }
}

class SNIP_Solution2 {
    /**
     * 没有完全理解递归的形式
     * https://blog.csdn.net/u013272948/article/details/53156041
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // 如果没有节点或者只有一个节点那么返回head
        if (head == null || head.next == null){
            return head;
        }

        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;

        return temp;
    }
}

class SNIP_Solution3 {
    public ListNode swapPairs(ListNode head) {

        // 如果没有节点或者只有一个节点那么返回head
        if (head == null || head.next == null)
            return head;

        //应为这个head就是第一个数据节点了, 我们自己构造一个没有数据的头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;  // dummy是一个没有数据的"头节点"
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            ListNode temp = cur.next.next;
            cur.next.next = temp.next;
            temp.next = cur.next;
            cur.next = temp;

            cur = temp.next;
        }

        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {};
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}