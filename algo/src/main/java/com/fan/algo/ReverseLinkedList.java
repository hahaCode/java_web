package com.fan.algo;

import java.util.Scanner;
//https://leetcode.cn/problems/reverse-linked-list/
public class ReverseLinkedList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String substring = s.trim().substring(8, s.trim().length()-1);
            String[] node = substring.trim().split(",");

            ListNodes p = null;
            for (int i = node.length-1;  i >= 0; i--) {
                ListNodes cur = new ListNodes();
                cur.val = Integer.parseInt(node[i]);
                cur.next = p;
                p = cur;
            }

            ListNodes head = reverseList(p);
        }
    }

    public static ListNodes reverseList(ListNodes head) {
        if (head == null)
            return null;

        ListNodes pre = null, cur = head;
        while (cur.next != null) {
           head = cur.next;
           cur.next = pre;
           pre = cur;
           cur = head;
        }
        cur.next = pre;
        return cur;
    }
}

class ListNodes {
    int val;
    ListNodes next;

    ListNodes() {
    }

    ListNodes(int val) {
        this.val = val;
    }

    ListNodes(int val, ListNodes next) {
        this.val = val;
        this.next = next;
    }
}
