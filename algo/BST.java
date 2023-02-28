import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class BST {
    public static void main(String[] args) {
        
        int[] arr = {45, 10, 7, 90, 12, 50, 13, 39, 57};
        BSTree tree = new BSTree();
        for(int i=0; i<arr.length; ++i) {
            tree.insert(arr[i]);
        }

        // successor
        Node successor = tree.successor(12);
        if( successor != null ) {
            System.out.println(successor.info);
        }
        else {
            System.out.println("BRAK");
        }

        // predecessor
        Node predecessor = tree.predecessor(57);
        if( predecessor != null ) {
            System.out.println(predecessor.info);
        }
        else {
            System.out.println("BRAK");
        }

        // preorder
        System.out.print("PREORDER: ");
        tree.printPreorder();

        // levelorder
        System.out.print("LEVELORDER: ");
        tree.printLevelorder();
        System.out.println();

        // postorder
        System.out.print("POSTORDER: ");
        tree.printPostorder();
        System.out.println();

        // inorder
        System.out.print("INORDER: ");
        tree.printInorder();
        System.out.println();

        // height
        System.out.println("HEIGHT REC : " + tree.findHeight_rec(tree.root));
        System.out.println("HEIGHT ITER: " + tree.findHeight_iter());


        // BFS - QUEUE
        // DFS - STACK
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

class BSTree {
    
    public Node root;
    public BSTree() {
        root = null;
    }

    public void printPreorder() {        
        Node curr;
        Stack<Node> stack = new Stack<>();
        stack.push(root); // push root
        while( !stack.isEmpty() ) {
            curr = stack.pop();
            System.out.print(curr.info + " ");
            if( curr.right != null ) { // the order is important!! First push curr.right, then curr.left
                stack.push(curr.right);
            }
            if( curr.left != null ) {
                stack.push(curr.left);
            }
        }
        System.out.println();
    }

    public void printLevelorder() {

        Node curr;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            System.out.print(curr.info + " ");
            if( curr.left != null ) {
                queue.add(curr.left);
            }
            if( curr.right != null ) {
                queue.add(curr.right);
            }
        }
    }

    public void printPostorder() {
        if( root == null ) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while( true ) {
            while( curr != null ) {
                stack.push(curr);
                stack.push(curr);
                curr = curr.left;
            }
            if( stack.isEmpty() ) return;

            curr = stack.pop();
            if( !stack.isEmpty() && curr == stack.peek() ) {
                curr = curr.right;
            }
            else {
                System.out.print(curr.info + " ");
                curr = null;
            }
        }
    }

    public void printInorder() {
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        while( curr != null || !stack.isEmpty() ) {
            while( curr != null ) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.info + " ");
            curr = curr.right;
        }
    }

    public Node successor(int x) {
        Node prev = null;
        Node curr = root;
        while( curr != null ) {
            if( x >= curr.info ) {
                curr = curr.right;
            }
            else {
                prev = curr;
                curr = curr.left;
            }
        }
        return prev;
    }

    public Node predecessor(int x) {
        Node prev = null;
        Node curr = root;
        while(curr != null) {
            if(x <= curr.info) {
                curr = curr.left;
            }
            else{
                prev = curr;
                curr = curr.right;
            }
        }
        return prev;
    }

    public int findHeight_rec(Node curr) {
        if( curr == null ) return -1;
        int l = findHeight_rec(curr.left);
        int r = findHeight_rec(curr.right);
        return ((l >= r) ? l : r) + 1;
    }

    public int findHeight_iter() {
        if( root == null ) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node curr;
        int height = -1;
        while( !queue.isEmpty() ) {
            ++height;
            int size = queue.size();

            for(int i=0; i<size; ++i) {
                curr = queue.poll();
                if( curr.left != null ) {
                    queue.add(curr.left);
                }
                if( curr.right != null ) {
                    queue.add(curr.right);
                }
            }
        }
        return height;
    }

    // delete
    public Node deleteNode(Node curr, int key) {
        if( curr == null ) return null;
        if( key > curr.info ) {
            curr.right = deleteNode(curr.right, key);
            return curr;
        }
        else if( key < curr.info ) {
            curr.left = deleteNode(curr.left, key);
            return curr;
        }
        else {
            if( curr.left != null && curr.right != null ) {
                int left_max = findMax(curr.left);
                curr.info = left_max;
                curr.left = deleteNode(curr.left, left_max);
                return curr;
            }
            else if( curr.left != null ) {
                return curr.left;
            }
            else if( curr.right != null ) {
                return curr.right;
            }
            else {
                return null;
            }
        }
    }
    
    public static int findMax(Node root) {
        while( root.right != null ) {
            root = root.right;
        }
        return root.info;
    }
    // end delete

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
