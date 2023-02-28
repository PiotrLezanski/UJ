import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        int k = 3;

        Node[] arr = new Node[k];

        arr[0] = new Node(1);
        arr[0].next = new Node(3);
        arr[0].next.next = new Node(5);
        arr[0].next.next.next = new Node(7);
  
        arr[1] = new Node(2);
        arr[1].next = new Node(4);
        arr[1].next.next = new Node(6);
        arr[1].next.next.next = new Node(8);
  
        arr[2] = new Node(0);
        arr[2].next = new Node(9);
        arr[2].next.next = new Node(10);
        arr[2].next.next.next = new Node(11);

        Heap heap = new Heap(arr, k);
        Node head = mergeKLists(heap);

        while( head != null ) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        
    }

    public static Node mergeKLists(Heap heap) {
        Node root = null;
        Node last = null;

        while( heap.size > 0 ) {
            Node min = heap.extractMin();

            if( min.next != null ) {
                heap.insert(min.next);
            }

            if( root == null ) {
                root = min;
                last = root;
            }
            else {
                last.next = min;
                last = min;
            }
            last.next = null;            
        }

        return root;
    }
}

class Heap {
    Node[] arr;
    int size;
    int max_size;
    Heap(Node[] heads, int x) {
        arr = heads;
        size = x;
        max_size = x;
        buildHeap();
    }

    public void buildHeap() {
        for(int i=(size/2)-1; i>=0; --i) {
            heapifyDown(i);
        }
    }

    public Node extractMin() {
        Node min = arr[0];
        arr[0] = arr[size-1];
        --size;
        return min;
    }

    public void insert(Node new_node) {
        if( size >= max_size ) {
            System.out.println("HEAP IS MAX");
        }
        else {
            arr[size++] = new_node;
        }
        heapifyUp(size-1);
    }

    public void heapifyUp(int i) {
        while( i > 0 ) {
            int parent = (i-1) / 2;
            if( arr[parent].value > arr[i].value ) {
                Node tmp = arr[parent];
                arr[parent] = arr[i];
                arr[i] = tmp;
                i = parent;
            }
            else break;
        }
    }

    public void heapifyDown(int i) {
        while( i < size/2 ) {
            int min = i;
            int l = (2*i) + 1;
            int r = (2*i) + 2;
            if( l < size && arr[l].value < arr[min].value ) {
                min = l;
            }
            if( r < size && arr[r].value < arr[min].value ) {
                min = r;
            }

            if( i != min ) {
                Node tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
                i = min;
            }
            else break;
        }
    }
}

class Node {
    int value;
    Node next;
    Node(int x) {
        value = x;
        next = null;
    }
}