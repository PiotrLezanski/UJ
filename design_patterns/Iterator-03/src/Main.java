public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(4.5);
        bst.insert(1);
        bst.insert(3.6);
        bst.insert(3.5);
        bst.insert(2.54);
        bst.insert(4.5);
        bst.insert(5.23);
        bst.insert(0.45);

        BSTIterator iterator = new BSTIterator(bst.root);

        while(iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }
    }
}