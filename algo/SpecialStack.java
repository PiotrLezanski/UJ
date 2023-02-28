import java.util.Scanner;

public class SpecialStack {
    public static void main(String[] args) {
        
        Stack stack = new Stack();
        stack.push(4);
        stack.push(2);
        stack.push(6);
        stack.push(9);
        stack.push(0);
        stack.push(50);
        stack.push(5); // 76/7
        System.out.println(stack.aver()); // 10
        stack.pop(); // 71/6
        System.out.println(stack.aver()); // 11

        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());

    }
}

class Stack {
    class Node {
        int val;
        Node prev;
        Node next;

        int curr_min;
        int stack_size;
        int sum;

        Node(int x) {
            val = x;
            prev = null;
            next = null;
            curr_min = 0;
            stack_size = 0;
            sum = 0;
        }
    }
    Node top;
    Stack() {
        top = null;
    }

    void push(int x) {
        Node new_node = new Node(x);
        if( top == null ) {
            top = new_node;
            top.curr_min = top.val;
            top.stack_size = 1;
            top.sum = new_node.val;
        }
        else {
            top.next = new_node;
            new_node.prev = top;
            new_node.sum = top.sum + new_node.val;
            new_node.stack_size = top.stack_size + 1;
            if( new_node.val < top.curr_min ) {
                new_node.curr_min = new_node.val;
            }
            else {
                new_node.curr_min = top.curr_min;
            }
            top = new_node;
        }
    }

    void pop() {
        top = top.prev;
    }

    int getMin() {
        return top.curr_min;
    }

    int aver() {
        return top.sum / top.stack_size;
    }

}