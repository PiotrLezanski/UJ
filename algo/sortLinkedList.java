import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class sortLinkedList {
    public static void main(String[] args) {
        
        LinkedList list = new LinkedList();
        list.push(4);
        list.push(1);
        list.push(5);
        list.push(-1);
        list.push(0);
        list.push(2);
        list.push(9);
        printList(list.head);

        Node sorted = mergeSort(list.head);
        printList(sorted);
        
    }

    public static Node mergeSort(Node curr) {
        if( curr == null || curr.next == null ) {
            return curr;
        }

        Node middle = getMiddle(curr);
        Node nextMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(curr);
        Node right = mergeSort(nextMiddle);

        Node sortedList = merge(left, right);
        return sortedList;
    }

    public static Node merge(Node left, Node right) {
        Node result = null;
        if( left == null ) {
            return right;
        }
        if( right == null ) {
            return left;
        }

        if( left.info <= right.info ) {
            result = left;
            result.next = merge(left.next, right);
        }
        else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    public static Node getMiddle(Node head) {
        if( head == null ) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while( fast.next != null && fast.next.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void printList(Node head) {
        while( head != null ) {
            System.out.print(head.info + " ");
            head = head.next;
        }
        System.out.println();
    }
}

class Node {
    int info;
    Node next;
    Node(int x) {
        info = x;
        next = null;
    }
}

class LinkedList {
    
    Node head;
    Node last;

    LinkedList() {
        head = null;
        last = null;
    }

    void push(int x) {
        Node new_node = new Node(x);
        if( head == null ) {
            head = new_node;
            last = head;
        }
        else {
            last.next = new_node;
            last = new_node;
        }
    }
}
