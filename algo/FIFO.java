// Piotr Lezanski 

import java.util.Arrays;
import java.util.Scanner;

class FifoList {
    private FifoList head;
    private FifoList next;

    private int number;

    FifoList() {}
    FifoList(int num) {
        number = num;
        next = null;
        head = null;
    }

    public void offer(int num) {
        FifoList new_fifo = new FifoList(num);

        if( head == null ) {
            head = new_fifo;
        }
        else {
            FifoList tmp = head;
            while( tmp.next != null ) {
                tmp = tmp.next;
            }
            tmp.next = new_fifo;
        }
    }

    public int poll() {
        if( head != null ) {
            FifoList old_head = head;
            head = head.next;

            return old_head.number;
        }
        return -1;
    }

    public int peek() {
        return head.number;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        for(FifoList tmp=head; tmp != null; tmp=tmp.next, size++) {}
        return size;
    }

    public void display() {
        for(FifoList tmp=head; tmp != null; tmp=tmp.next) {
            System.out.print(tmp.number + " ");
        }
        System.out.println();
    }
}

class Test {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        FifoList fifoList = new FifoList();
        fifoList.offer(1);
        fifoList.offer(2);
        fifoList.offer(3);
        fifoList.offer(4);
        fifoList.display();
        System.out.println(fifoList.isEmpty());
        System.out.println(fifoList.peek());
        System.out.println(fifoList.poll());
        fifoList.display();
        System.out.println(fifoList.isEmpty());
    }
}
