import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import java.util.Arrays;

public class BFS_DFS {
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

        System.out.print("DFS iter: ");
        iterativeDFS(root);
        System.out.println();

        System.out.print("DFS rec: ");
        recursiveDFS(root);
        System.out.println();

        System.out.print("BFS: ");
        BFS(root);
        System.out.println();

    }

    public static void recursiveDFS(Node curr) {
        System.out.print(curr.value + " ");
        for(int i=0; i<curr.children.size(); ++i) {
            recursiveDFS(curr.children.get(i));
        }
    }

    public static void iterativeDFS(Node root) {
        Node curr;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while( !stack.isEmpty() ) {
            curr = stack.pop();
            System.out.print(curr.value + " ");

            for(int i=0; i<curr.children.size(); ++i) {
                stack.push(curr.children.get(i));
            }
        }
    }

    public static void BFS(Node root) {
        Node curr;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            System.out.print(curr.value + " ");
            for(int i=0; i<curr.children.size(); ++i) {
                queue.add(curr.children.get(i));
            }
        }
    }
}

class Node {
    public int value;
    public Vector<Node> children;
    Node(int x) {
        value = x;
        children = new Vector<>();
    }
}
