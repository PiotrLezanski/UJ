import java.util.Scanner;

class BstTree {
    class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
            left = null;
            right = null;
        }
    }
    public Node root;

    BstTree() {
        root = null;
    }

    public void insert(int key) {
        if( root == null ) {
            root = new Node(key);
        }
        else {
            Node prev = root;
            Node curr = root;
            while( curr != null ) {
                prev = curr;
                if( curr.value < key ) {
                    curr = curr.right;
                }
                else if( curr.value > key ) {
                    curr = curr.left;
                }
            }

            if( prev.value > key ) {
                prev.left = new Node(key);
            }
            else if( prev.value < key ) {
                prev.right = new Node(key);
            }
        }
    }

    public void delete_iterative(int key) {
        Node prev = null;
        Node curr = root;
        while( curr != null && curr.value != key ) {
            prev = curr;
            if( key < curr.value ) {
                curr = curr.left;
            }
            else if( key > curr.value ) {
                curr = curr.right;
            }
        }

        if( curr == null ) {
            System.out.println("BRAK");
        }
        else {
            if( curr.left == null && curr.right == null ) {
                if( curr == prev.left ) {
                    prev.left = null;
                }
                else if( curr == prev.right ) {
                    prev.right = null;
                }
            }
            else if( curr.left == null ) {
                if( curr == prev.left ) {
                    prev.left = curr.right;
                }
                else if( curr == prev.right ) {
                    prev.right = curr.right;
                }
            }
            else if( curr.right == null ) {
                if( curr == prev.left ) {
                    prev.left = curr.left;
                }
                else if( curr == prev.right ) {
                    prev.right = curr.left;
                }
            }
            else {

            }
        }
    }

    public Node delete_recursive(Node curr, int key) {
        if( curr == null ) return curr;

        if( key < curr.value ) {
            root.left = delete_recursive(curr.left, key);
        }
        else if( key > curr.value ) {
            root.right = delete_recursive(curr.right, key);
        }
        else { // note to be deleted is found
            // node with only one child or none
            if( curr.left == null ) {
                return curr.right;
            }
            else if( curr.right == null ) {
                return curr.left;
            }

            // node with two children
            curr.value = minValue(curr.right); // get minimum element in the right subtree (inorder successor)
            
            curr.right = delete_recursive(curr.right, curr.value); // delete duplicate in right subtree
        }   
        return curr;
    }

    public static int minValue(Node curr) {
        while( curr.left != null ) {
            curr = curr.left;
        }
        return curr.value;
    }


    public int findHeight(Node curr) {
        if( curr == null ) return -1;
        else {
            int l = findHeight(curr.left);
            int r = findHeight(curr.right);
            return (l > r ? l : r) + 1;
        }
    }

    public Node findNext(int x) {   
        Node successor = null;
        Node curr = root;
        while (curr != null) {
            if (x >= curr.value) {
                curr = curr.right;
            } 
            else {
                successor = curr;
                curr = curr.left;
            }
        }
        return successor;
    }

    public Node findPrev(int x) {
        Node predecessor = null;
        Node curr = root;

        while(curr != null){
            if(x <= curr.value){
                curr = curr.left;
            }
            else {
                predecessor = curr;
                curr = curr.right;
            }
        }
        return predecessor;
    }

    // in preorder root is always at the first spot
    void printPreorder(Node curr) {
        if( curr == null ) return;
        System.out.print(curr.value + " ");
        printPreorder(curr.left);
        printPreorder(curr.right);
    }

    void printInorder(Node curr) {
        if( curr == null ) return;
        printInorder(curr.left);
        System.out.print(curr.value + " ");
        printInorder(curr.right);
    }


    // in postorder traversal, last element is always a root
    /*
              10
            /   \
            5     40    ---> 1, 7, 5, 50, 40, 10 
          /  \      \
         1    7      50
    */
    void printPostorder(Node curr) {
        if( curr == null ) return;
        printPostorder(curr.left);
        printPostorder(curr.right);
        System.out.print(curr.value + " ");
    }
}


class Trees {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {

        int[] arr = {11,53,13,3,2,6,9,35,4};
        
        BstTree tree = new BstTree();
        for(int i=0; i<arr.length; ++i) {
            tree.insert(arr[i]);
        }

        System.out.println(tree.findPrev(6).value);

        // System.out.println(tree.findHeight(tree.root));

        // System.out.print("PREORDER: ");
        // tree.printPreorder(tree.root);
        // System.out.println();

        // System.out.print("INORDER: ");
        // tree.printInorder(tree.root);
        // System.out.println();

        // System.out.print("POSTORDER: ");
        // tree.printPostorder(tree.root);
        // System.out.println();

    }
}