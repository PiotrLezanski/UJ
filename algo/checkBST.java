import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class checkBST {
    public static void main(String[] args) {

        BSTree tree = new BSTree();
        tree.root = new Node(0);

        // tree.root = new Node(3);
        // tree.root.left = new Node(2);
        // tree.root.left.left = new Node(1);
        // tree.root.left.right = new Node(4);

        // tree.root.right = new Node(5);

        System.out.println(isBST(tree));
        System.out.println(isBST_rec(tree));
    }

    public static boolean isBST_rec(BSTree tree) {
        return checkBST_rec(tree.root);
    }

    static Node prev;
    public static boolean checkBST_rec(Node curr) {
        if( curr == null ) return true;
        if( !checkBST_rec(curr.left) ) return false;
        if( prev != null && curr != null && curr.info < prev.info ) return false;
        prev = curr;
        return checkBST_rec(curr.right);
    }

    public static boolean isBST(BSTree tree) {
        Stack<Node> stack = new Stack<>();
        int prev = Integer.MAX_VALUE;
        Node curr = tree.root;
        while( curr != null || !stack.isEmpty() ) {
            while( curr != null ) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if( curr.info > prev ) return false;
            prev = curr.info;
            curr = curr.right;
        }
        return true;
    }
}

class BSTree {
    
    public Node root;
    public BSTree() {
        root = null;
    }

    public void insert(int x) {
        Node new_node = new Node(x);
        if( root == null ) {
            root = new_node;
        }
        else {
            Node prev = null;
            Node curr = root;
            while( curr != null ) {
                prev = curr;
                if( x < curr.info ) {
                    curr = curr.left;
                }
                else {
                    curr = curr.right;
                }
            }

            if( x < prev.info ) {
                prev.left = new_node;
            }
            else {
                prev.right = new_node;
            }
        }
    }
}

class Node {
    public int info;
    public Node left;
    public Node right;
    Node(int x) {
        info = x;
        left = null;
        right = null;
    }
}
