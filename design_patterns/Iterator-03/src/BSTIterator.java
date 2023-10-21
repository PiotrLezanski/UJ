import java.util.Stack;

public class BSTIterator
{
    BST.Node root;
    Stack<BST.Node> stack;

    BSTIterator(BST.Node root)
    {
        this.root = root;
        stack = new Stack<>();
        pushLeftNodesOnStack(this.root);
    }

    void pushLeftNodesOnStack(BST.Node curr)
    {
        while(curr != null)
        {
            stack.push(curr);
            curr = curr.getLeft();
        }
    }

    boolean hasNext()
    {
        return !stack.isEmpty();
    }

    double next()
    {
        if(!hasNext())
        {
            throw new IllegalStateException("No more elements in the tree");
        }

        BST.Node curr = stack.pop();
        pushLeftNodesOnStack(curr.getRight());
        return curr.getValue();
    }
}
