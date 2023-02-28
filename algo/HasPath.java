import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class HasPath {
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

        System.out.println(hasPath_iterBFS(root, root.children.get(2)));
        System.out.println(hasPath_rec(root, root.children.get(2)));
        System.out.println(hasPath_iterDFS(root, root.children.get(2)));

    }

    // DFS
    public static boolean hasPath_rec(Node src, Node dest) {
        if( src == dest ) {
            return true;
        }

        for(int i=0; i<src.children.size(); ++i) {
            if(hasPath_rec(src.children.get(i), dest)) {
                return true;
            }
        }
        return false;
    }

    // DFS iter
    public static boolean hasPath_iterDFS(Node src, Node dest) {
        Stack<Node> stack = new Stack<>();
        stack.push(src);
        Node curr;
        while( !stack.isEmpty() ) {
            curr = stack.pop();
            if( curr == dest ) {
                return true;
            }
            for(int i=0; i<curr.children.size(); ++i) {
                stack.push(curr.children.get(i));
            }
        }
        return false;
    }

    // BFS
    public static boolean hasPath_iterBFS(Node src, Node dest) {
        Node curr;
        Queue<Node> queue = new LinkedList<>();
        queue.add(src);
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            if( curr == dest ) {
                return true;
            }
            for(int i=0; i<curr.children.size(); ++i) {
                queue.add(curr.children.get(i));
            }
        }
        return false;
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


