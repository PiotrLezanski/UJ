import java.util.Stack;

public class BST
{
    class Node
    {
        private double value;
        private Node left;
        private Node right;

        Node(double value)
        {
            this.value = value;
            left = null;
            right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
    Node root;

    public BST()
    {
        root = null;
    }

    public void insert(double value)
    {
        Node new_node = new Node(value);
        if( root == null )
        {
            root = new_node;
        }
        else
        {
            Node prev = null;
            Node curr = root;
            while( curr != null )
            {
                prev = curr;
                if(value < curr.getValue())
                {
                    curr = curr.left;
                }
                else
                {
                    curr = curr.right;
                }
            }

            if(value < prev.getValue())
            {
                prev.left = new_node;
            }
            else
            {
                prev.right = new_node;
            }
        }
    }
}
