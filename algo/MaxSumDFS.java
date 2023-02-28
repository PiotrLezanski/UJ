import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class MaxSumDFS {
    public static void main(String[] args) {
        
        Node root = new Node(5);
        (root.children).add(new Node(-2));
        (root.children).add(new Node(-1));
        (root.children).add(new Node(-7));
        (root.children.get(0).children).add(new Node(7));
        (root.children.get(0).children).add(new Node(4));
        (root.children.get(1).children).add(new Node(3));
        (root.children.get(2).children).add(new Node(4));
        (root.children.get(2).children).add(new Node(10));
        (root.children.get(2).children).add(new Node(5));

        (root.children.get(0).children.get(0).children).add(new Node(-2));
        (root.children.get(2).children.get(0).children).add(new Node(7));
        (root.children.get(2).children.get(0).children).add(new Node(-3));

        // System.out.println(recursiveDFS(root, root.value));
        System.out.println(recursiveDFS2(root, 0));
        // System.out.println(iterativeDFS(root));

    }

    static int maxSum = Integer.MIN_VALUE;
    public static int recursiveDFS(Node curr, int curr_sum) {
        maxSum = Math.max(maxSum, curr_sum);

        for(int i=0; i<curr.children.size(); ++i) {
            recursiveDFS(curr.children.get(i), curr_sum + curr.children.get(i).value);
        }
        return maxSum;
    }

    public static int iterativeDFS(Node root) {
        Stack<Node> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack.push(root);
        stack2.push(root.value);
        Node curr;
        int curr_sum;
        int max_sum = Integer.MIN_VALUE;
        while( !stack.isEmpty() ) {
            curr = stack.pop();
            curr_sum = stack2.pop();
            max_sum = Math.max(max_sum, curr_sum);
            for(int i=0; i<curr.children.size(); ++i) {
                stack.push(curr.children.get(i));
                stack2.push(curr_sum + curr.children.get(i).value);
            }
        }
        return max_sum;
    }

    public static int recursiveDFS_toleaf(Node curr, int curr_sum) {
        if( curr.children.size() == 0 ) {
            maxSum = Math.max(maxSum, curr_sum);
        }

        for(int i=0; i<curr.children.size(); ++i) {
            recursiveDFS_toleaf(curr.children.get(i), curr_sum + curr.children.get(i).value);
        }
        return maxSum;
    }
}

class Node {
    public int value;
    // public Node[] children;
    public Vector<Node> children;
    Node(int x) {
        value = x;
        children = new Vector<>();
    }
}

