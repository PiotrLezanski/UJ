/*

Each node in a binary tree contains an integer value and two pointers to next-level nodes: “left” and “right”. We call a tree sorted if for each node the following statement is correct:

“All values in the left subtree are less than or equal to the value of the node, and all values in the right subtree are greater than the value of the node.”

Write a function that takes a pointer to a root of a tree and checks whether the tree is sorted. You may create more than one method, if necessary.

*/

#include <vector>
#include <iostream>
#include <stack>
using namespace std;

struct Node {
public:
  Node(int val) : value(val), pLeft(NULL), pRight(NULL) {};
  
  ~Node() {
    if (pLeft)
      delete pLeft;
    if (pRight)
      delete pRight;
  };
  
  int value;
  Node * pLeft, * pRight;
};

// ******************** YOUR CODE HERE ********************
// idea: Inorder traversal of BST tree will for sure give a sorted array. Using this fact I am checking
//         whether vector filled with inorder traversal is sorted in non-decreasing order.
//        I chose to implement iterative inorder traversal, to make my solution more efficient.
//         However there is one corner case, when the inorder traversal of the tree is sorted. 
//          Namely nodes with the same value can be ONLY in left subtree, not right. (according to definition)
//          There can't be a node in right subtree, that has the same value as its parent. It is very easy to check, just add
//              one more if into inorder function.

// Time complexity: O(n)
// Space complexity: O(n)

bool inorder(vector<int>& inord, Node *root)
{
    stack<Node *> s;
    Node *curr = root;
    while (curr != NULL || s.empty() == false)
    {
        while (curr !=  NULL)
        {
            s.push(curr);
            curr = curr->pLeft;
        }
        curr = s.top(); s.pop();
        if( curr->pRight != NULL && curr->value == curr->pRight->value ) return false;
        inord.push_back(curr->value);
        curr = curr->pRight;
    } 
    return true;
}

// function to check whether vector is sorted non-decreasing
bool check(vector<int> nums) {
    for(int i=0; i<nums.size()-1; ++i) {
        if( nums[i] > nums[i+1] ) 
            return false;
    }
    return true;
}

// main function
bool IsSorted(Node * pRoot){
    vector<int> inord;
    if( !inorder(inord, pRoot) ) return false;
    return check(inord);
}

// ********************************************************

int main() {
  
  // This is only simple example.
  // Solution must handle all cases.

    Node* pRoot = new Node(2);

    pRoot->pLeft = new Node(2);
    pRoot->pRight = new Node(6);

    pRoot->pLeft->pLeft = new Node(2);
    // pRoot->pLeft->pRight = new Node(3);
    pRoot->pRight->pLeft = new Node(4);
    pRoot->pRight->pRight = new Node(6);
  
    if (IsSorted(pRoot)) cout << "Tree is sorted" << endl;
    else cout << "Tree is not sorted" << endl;
    
    delete pRoot;
    return 0;
}
