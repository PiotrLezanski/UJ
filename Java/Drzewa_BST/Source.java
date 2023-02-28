// Piotr Lezanski - 4
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

class Index {
    int index;
    Index(int i) {
        index = i;
    }

    void increment() {
        ++index;
    }

    void decrement() {
        --index;
    }
}

class Node {
    Person info;
    Node left, right;
    
    Node(Person key) {
        this.info = key;
        this.left = null;
        this.right = null;
    }

    Node(Person key, Node l, Node r) {
        this.info = key;
        left = l;
        right = r;
    }
}

class Tree {
    

    Tree(Node root) {
        this.root = root;
    }
    public Node root;

    void printInorder() {
        if (root == null) return;
        Node curr = root;
 
        Stack<Node> s = new Stack<Node>();
        boolean isFirst = false;
        // traverse the tree
        while (curr != null || !s.isEmpty()) {
 
            /* Reach the left most Node of the
            curr Node */
            while (curr !=  null) {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                s.push(curr);
                curr = curr.left;
            }
 
            /* Current must be null at this point */
            curr = s.pop();
 
            if( isFirst ) System.out.print(", " + curr.info.priority + " - " + curr.info.name + " " + curr.info.surname);
            else {
                isFirst = true;
                System.out.print(curr.info.priority + " - " + curr.info.name + " " + curr.info.surname);
            }
            curr = curr.right;
        }
    }

    void printPreorder() {
        // Base Case
        Node node = root;
        
        if (node == null) return;

        // Create an empty stack and push root to it
        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(root);
        boolean isFirst = false;
        while (!nodeStack.isEmpty()) {
 
            // Pop the top item from stack and print it
            Node mynode = nodeStack.peek();

            if( isFirst ) System.out.print(", " + mynode.info.priority + " - " + mynode.info.name + " " + mynode.info.surname);
            else {
                isFirst = true;
                System.out.print(mynode.info.priority + " - " + mynode.info.name + " " + mynode.info.surname);
            }

            nodeStack.pop();
 
            // Push right and left children of the popped node to stack
            if (mynode.right != null) {
                nodeStack.push(mynode.right);
            }
            if (mynode.left != null) {
                nodeStack.push(mynode.left);
            }
        }
    }

    public Person DEQUEMIN ()
    {       Node t=root;
        if(root!=null) {
            Person minv = t.info;
            if (root.left == null) {
             //   root = null;
                return minv;
            } else {
                minv = root.left.info;
                while (t.left.left != null) {
                    minv = t.left.left.info;
                    t = t.left;
                }
                return minv;
            }
        }return null;
    }

    Person deleteNode(int key) {
        Node curr = root;
        Node prev = null;
    
        // Check if the key is actually
        // present in the BST.
        // the variable prev points to
        // the parent of the key to be deleted.


        while (curr != null && curr.info.priority != key) {
            prev = curr;
            if (key < curr.info.priority) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }
    
        if (curr != null) {
            if (curr.left == null || curr.right == null) {
    
                // newCurr will replace
                // the node to be deleted.
                Node newCurr;
        
                // if the left child does not exist.
                if (curr.left == null) {
                    newCurr = curr.right;
                }
                else {
                    newCurr = curr.left;
                }
        
                // check if the node to
                // be deleted is the root.
                if (prev == null) {
                    Person tmp_per = root.info;
                    // root = null;

                    if( root.left != null && root.right == null ) root = root.left;
                    else if( root.right != null && root.left == null ) root = root.right;
                    else  if( root.right == null && root.left == null ) {
                        root = null;
                    }
                    else if( root.right != null && root.left != null ) {
                        // root.left.right = root.right;
                        // root = root.left;
                        root.right.left = root.left;
                        root = root.right;
                    }
                    return tmp_per;
                }
        
                // check if the node to be deleted
                // is prev's left or right child
                // and then replace this with newCurr
                if (curr == prev.left) {
                    prev.left = newCurr;
                }
                else {
                    prev.right = newCurr;
                }

                if( curr == root ) root = null; // delete root
                return curr.info;
            }
            // node to be deleted has
            // two children.
            else {
                Node p = null;
                Node temp;
        
                // Compute the inorder successor
                temp = curr.right;
                while (temp.left != null) {
                    p = temp;
                    temp = temp.left;
                }
        
                // check if the parent of the inorder
                // successor is the curr or not(i.e. curr=
                // the node which has the same data as
                // the given data by the user to be
                // deleted). if it isn't, then make the
                // the left child of its parent equal to
                // the inorder successor'd right child.
                if (p != null) {
                    p.left = temp.right;
                }
        
                // if the inorder successor was the
                // curr (i.e. curr = the node which has the
                // same data as the given data by the
                // user to be deleted), then make the
                // right child of the node to be
                // deleted equal to the right child of
                // the inorder successor.
                else {
                    curr.right = temp.right;
                }
                Person tmp_per = curr.info;
                curr.info = temp.info;

                // if( tmp_per == root.info ) root = null;
                return tmp_per;
            }
        }
        else {
            return null;
        }
    }

    public Person dequeMax ()
    {
        Node t=root;
        if(root!=null) {
            Person minv = t.info;
            if (root.right == null) {
                return minv;
            } 
            else {
                minv = t.right.info;
                while (t.right.right != null) {
                    minv = t.right.right.info;
                    t = t.right;
                }

                return minv;
            }
        }
        return null;
    }


    void printPostorder() {
        Node curr = root;
        if( curr == null) return;
        Stack <Node> s = new Stack<>();
        
        Node prev = null;  // To Keep track of printed right child so that we can't print them twice
        boolean isFirst = false;
        while( curr != null || s.isEmpty() == false)
        {
            if(curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            else {
                curr = s.peek();
                if(curr.right == null || curr.right == prev)
                {
                    if( isFirst ) System.out.print(", " + curr.info.priority + " - " + curr.info.name + " " + curr.info.surname);
                    else {
                        isFirst = true;
                        System.out.print(curr.info.priority + " - " + curr.info.name + " " + curr.info.surname);
                    }
                    s.pop();
                    prev = curr;
                    curr = null;
                }
                else {
                    curr = curr.right;
                }
            } 
        }
    }
    
    int findHeight(Node curr) {
        if( root != null ) {
            if( curr == null ) {
                return -1;
            }
    
            int left = findHeight(curr.left);
            int right = findHeight(curr.right);
    
            int max = (left >= right) ? left : right;
            return max + 1;
        }
        else {
            return 0;
        }
    }

    public void INSERT(Person key)
    {
        // start with th root node
        Node curr = root;

        // pointer to store the parent of the current node
        Node parent = null;

        // if the tree is empty, create a new node and set it as root
        if (root == null) {
             root=new Node(key);
        }

        // traverse the tree and find the parent node of the given key
        while (curr != null)
        {
            parent = curr;
            if (key.priority < curr.info.priority) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }
        if (key.priority < parent.info.priority) {
            parent.left = new Node(key);
        }
        else {
            parent.right = new Node(key);
        }
    }

    private Node min(Node t) { // linear complexity
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }
    public Node exist(int k){
        Node n=ifNodeExists(root,k);
        if(n!=null){
            return inOrderSuccessor(n);
        }else{
            return null;
        }
    }
    public Node exist2(int k){
        Node n=ifNodeExists(root,k);
        if(n!=null){
            return pred(n);
        }else{
            return null;
        }
    }

    public Node pred(Node n){
        Node pre=null;
        Node t=root;
        while(t!=null){
            if(t.info.priority>=n.info.priority){
                t=t.left;
            }else{
                pre=t;
                t=t.right;
            }
        }
        return pre;
    }

    Node pred(int priority) {
        Node prev = root;
        Node curr = root;
        while( curr != null ) {
            if( priority > curr.info.priority ) {
                prev = curr; // trzymam ostatni mniejszy element
                curr = curr.right;
            }
            else if( priority < curr.info.priority ) {
                curr = curr.left;
            }
            else {
                break;
            }
        }

        if( curr != null ) {
            if( curr.left != null ) {
                curr = curr.left;
                while( curr.right != null ) {
                    curr = curr.right;
                }

                if( prev.info.priority < curr.info.priority ) {
                    return prev;
                }
                else {
                    return curr;
                }
            }
            else {
                if( prev.info.priority < priority ) {
                    return prev;
                }
                else {
                    return null;
                }          
            }
        }
        
        return null;
    }

    public Node ifNodeExists( Node node, int key)
    {
        if (node == null)
            return null;

        if (node.info.priority == key)
            return node;

        Node res1 = ifNodeExists(node.left, key);

        if(res1!=null) return  res1;
        Node res2 = ifNodeExists(node.right, key);

        return res2;
    }


    
    public Node inOrderSuccessor(Node n)
    {   Node successor = null;
        Node t=root;
        while (t != null) {

            if (n.info.priority >= t.info.priority) {
                t = t.right;
            } else {
                successor = t;
                t = t.left;
            }
        }

        return successor;
    }
    public Node minValue(Node node)
    {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
        {
            current = current.left;
        }
        return current;
    }
    public Node successor(int x) {
        Node t = root;
        Node s = null;

        while (t != null) {
            if (t.info.priority == x) {
                if(t.right != null)
                {
                    return min(t.right); }
                else {return  s;}
            } else if (t.info.priority < x) {
                t = t.right;
            } else {
                s = t;
                t = t.left;
            }
        }

        return null;
    }
    private Node max(Node t) {
        while (t.right != null) {
            t = t.right;
        }
        return t;
    }
    public Node predecessor(int x) {
        Node t = root;
        Node p = null;

        while (t != null) {
            if (t.info.priority == x) {
                if(t.left != null) return max(t.left);
                else return p;
            } else if (t.info.priority> x) {
                p = t;
                t = t.right;
            } else {
                t = t.left;
            }
        }

        return null;
    }
    public Node insert( Person key)
    {
        Node newnode =  new Node(key);
        Node x = root;

        Node y = null;

        while (x != null) {
            y = x;
            if (key.priority < x.info.priority)
                x = x.left;
            else
                x = x.right;
        }
        if (y == null)
            y = newnode;
        else if (key.priority < y.info.priority)
            y.left = newnode;
        else
            y.right = newnode;
        return y;
    }
}
class Person {
    public int priority;
    public String name;
    public String surname;

    Person(int p, String n, String s) {
        priority = p;
        name = n;
        surname = s;
    }
}
class Source{
    public static Scanner sc = new Scanner(System.in);
    static Tree tree = null;
    public static void main(String[] args) {

        int sessions = sc.nextInt();
        for(int s=0; s<sessions; ++s) {
            int m = sc.nextInt();
            System.out.println("ZESTAW " + (s+1));
            for(int x=0; x<m; ++x) {
                Node tmp;
                String command = sc.next();
                switch (command) {
                    case "CREATE":
                        String order = sc.next();
                        int n = sc.nextInt();
                        Person[] list = new Person[n];
                        for(int i=0; i<n; ++i) {
                            int prio = sc.nextInt();
                            String name = sc.next();
                            String surn = sc.next();
                            Person new_person = new Person(prio, name, surn);
                            list[i] = new_person;
                        }

                        if( order.equals("POSTORDER") ) {
                            tree = new Tree(buildPostorder(list));
                        }
                        else if( order.equals("PREORDER") ) {
                            tree = new Tree(buildPreorder(list));
                        }
                    break;

                    case "DEQUEMIN":
                        Person person=tree.DEQUEMIN();
                        if(person == null) {
                            System.out.println("DEQUEMIN: BRAK");
                            
                        }else{
                            System.out.println("DEQUEMIN: " + person.priority + " - " + person.name + " " + person.surname);
                            tree.deleteNode(person.priority);
                        }
                    break;

                    case "POSTORDER":
                        System.out.print("POSTORDER: ");
                        tree.printPostorder();
                        System.out.println();
                    break;

                    case "PREORDER":
                        System.out.print("PREORDER: ");
                        tree.printPreorder();
                        System.out.println();
                    break;
                    
                    case "HEIGHT":
                        Node curr = tree.root;
                        System.out.println("HEIGHT: " + tree.findHeight(curr));
                    break;
                    
                    

                    case "DEQUEMAX":
                        person =tree.dequeMax();
                        if(person!=null) {
                            System.out.println("DEQUEMAX: " + person.priority + " - " + person.name + " " + person.surname);
                            tree.deleteNode(person.priority);
                        }else{
                            System.out.println("DEQUEMAX: BRAK");
                        }
                    break;

                    case "DELETE":
                        int prio = sc.nextInt();
                        person = tree.deleteNode(prio);
                        if( person == null ) {
                            System.out.println("DELETE " + prio + ": BRAK");
                        }
                    break;

                    case "INORDER":
                        System.out.print("INORDER: ");
                        tree.printInorder();
                        System.out.println();
                    break;

                    case "ENQUE":
                        prio = sc.nextInt();
                        String name = sc.next();
                        String surn = sc.next();
                        person = new Person(prio, name, surn);
                        tree.INSERT(person);
                    break;

                    case "PREV":
                        prio = sc.nextInt();
                        tmp=tree.exist2(prio);
                        if(tmp!=null){
                            System.out.println("PREV "+ prio +": "+tmp.info.priority+" - "+tmp.info.name+" "+tmp.info.surname);
                        }
                        else{
                            System.out.println("PREV "+ prio +": BRAK");
                        }
                    break;

                    case "NEXT":
                        prio = sc.nextInt();
                        Node tem=tree.exist(prio);
                        if(tem!=null){
                            System.out.println("NEXT "+ prio +": "+tem.info.priority+" - "+tem.info.name+" "+tem.info.surname);
                        }
                        else{
                            System.out.println("NEXT "+ prio +": BRAK");
                        }
                    break;
                    
                }
            }
        }
    }


    // ~~~~~~~~ PREORDER ~~~~~~~~~
    public static Node buildPreorder(Person[] persons)
    {
        // start from the root node (the first element in a preorder sequence)
        // `AtomicInteger` is used here since `Integer` is passed by value in Java
        Index index = new Index(0);
 
        // set the root node's range as [-INFINITY, INFINITY] and recur
        return buildPreorder(persons, index, -1000000, 1000000);
    }
    // Recursive function to build a BST from a preorder sequence
    public static Node buildPreorder(Person[] persons, Index index, int min, int max)
    {
        // Base case
        if (index.index >= persons.length) {
            return null;
        }
 
        // Return if the next element of preorder traversal is not in the valid range
        int val = persons[index.index].priority;
        if (val < min || val > max) {
            return null;
        }
 
        // Construct the root node and increment `pIndex`
        Node root = new Node(persons[index.index], null, null);
        index.increment();
 
        // Since all elements in the left subtree of a BST must be less
        // than the root node's value, set range as `[min, val-1]` and recur
        root.left = buildPreorder(persons, index, min, val - 1);
 
        // Since all elements in the right subtree of a BST must be greater
        // than the root node's value, set range as `[val+1max]` and recur
        root.right = buildPreorder(persons, index, val + 1, max);
 
        return root;
    }
    // ~~~~~~~~ END PREORDER ~~~~~~~~~

    // ~~~~~~~~ POSTOTDER ~~~~~~~~~
    public static Node buildPostorder(Person[] persons)
    {
        // start from the root node (last element in postorder sequence)
        Index index = new Index(persons.length - 1);
 
        // set the root node's range as [-INFINITY, INFINITY] and recur
        return buildPostorder(persons, index, -1000000, 1000000);
    }
    public static Node buildPostorder(Person[] persons, Index index, int min, int max)
    {
        // Base case
        if (index.index < 0) {
            return null;
        }
 
        // Return if the next element of postorder traversal from the end
        // is not in the valid range
        int curr = persons[index.index].priority;
        if (curr < min || curr > max) {
            return null;
        }
 
        // Construct the root node and decrement `pIndex`
        Node root = new Node(persons[index.index], null, null);
        index.decrement();
 
        /* Construct the left and right subtree of the root node.
           Build the right subtree before the left subtree since the values
           are being read from the end of the postorder sequence. */
 
        // Since all elements in the right subtree of a BST must be greater
        // than the root node's value, set range as `[curr+1max]` and recur
        root.right = buildPostorder(persons, index, curr + 1, max);
 
        // Since all elements in the left subtree of a BST must be less
        // than the root node's value, set range as `[min, curr-1]` and recur
        root.left = buildPostorder(persons, index, min, curr - 1);
 
        return root;
    }

    // ~~~~~~~~ END POSTOTDER ~~~~~~~~~
}




class Stack<T> {
    private Node top;
    private class Node {
        T data;
        Node prev;

        public Node(T x, Node p) {
            data = x;
            prev = p;
        }
        public Node(T x) {
            data = x;
            prev = null;
        }
    }

    public Stack() {
        top = null;
    }

    public Stack(Node t) {
        top = t;
    }

    void push(T x) {
        if (top == null) {
            top = new Node(x);
        } else {
            top = new Node(x, top);
        }
    }

    T pop() {
        T temp = top.data;
        top = top.prev;
        return temp;
    }

    boolean isEmpty() {
        return top == null;
    }

    T peek() {
        return top.data;
    }
}

/*
1
4
CREATE PREORDER 5 51 Adam Nowak 33 Anna Kowalska 44 Marek Mickiewicz 43 Zofia Krzak 47 Ola Nowicka
POSTORDER
DEQUEMAX
POSTORDER

1
4
CREATE PREORDER 5 51 Adam Nowak 33 Anna Kowalska 44 Marek Mickiewicz 43 Zofia Krzak 47 Ola Nowicka
POSTORDER
DEQUEMIN
POSTORDER


1 
4
CREATE POSTORDER 5 12 Adam Nowak 33 Anna Kowalska 30 Marek Mickiewicz 43 Zofia Krzak 37 Ola Nowicka
POSTORDER
DEQUEMIN
POSTORDER

1 
4
CREATE POSTORDER 5 12 Adam Nowak 33 Anna Kowalska 30 Marek Mickiewicz 43 Zofia Krzak 37 Ola Nowicka
POSTORDER
DEQUEMAX
POSTORDER

1 
5
CREATE POSTORDER 5 12 Adam Nowak 33 Anna Kowalska 30 Marek Mickiewicz 43 Zofia Krzak 37 Ola Nowicka
POSTORDER
DEQUEMIN
DEQUEMAX
POSTORDER



1 
14
CREATE POSTORDER 5 12 Adam Nowak 33 Anna Kowalska 30 Marek Mickiewicz 43 Zofia Krzak 37 Ola Nowicka
POSTORDER
PREORDER
INORDER
DEQUEMAX
DEQUEMIN
INORDER
NEXT 33
NEXT 93
PREV 50
POSTORDER
ENQUE 35 Andrzej Wolny
INORDER
HEIGHT
*/
