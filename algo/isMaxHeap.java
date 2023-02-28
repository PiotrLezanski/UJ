import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class isMaxHeap {
    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.root = new Node(5);
        tree.root.left = new Node(4);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(2);

        tree.root.right = new Node(3);

        tree.root.left.parent = tree.root;
        tree.root.right.parent = tree.root;
        tree.root.left.left.parent = tree.root.left;
        tree.root.left.right.parent = tree.root.left;

        System.out.println(isMaxHeap(tree));
    }

    public static boolean isMaxHeap(BSTree tree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree.root);
        Node curr;
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            if( curr.parent != null && curr.parent.info < curr.info ) {
                return false;
            }
            if( curr.left != null ) {
                queue.add(curr.left);
            }
            if( curr.right != null ) {
                queue.add(curr.right);
            }
        }
        return true;
    }
}

class BSTree {
    public Node root;
    public BSTree() {
        root = null;
    }
}

class Node {
    public int info;
    public Node left;
    public Node right;
    public Node parent;
    Node(int x) {
        info = x;
        left = null;
        right = null;
    }
}
