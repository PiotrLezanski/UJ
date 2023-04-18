/*

Write a routine that does the following: for a given value n, create and initialize an n×n matrix. Deduce the initialization algorithm from the following examples:

Example 1: n = 2, the matrix is:

1 2
4 3 

Example 2: n = 4, the matrix is:

 1   2   3   4
12  13  14   5
11  16  15   6
10   9   8   7

*/


// ******************** YOUR CODE HERE ********************
// idea: to my mind, that looks like typical recursive problem. It is simmilar to some kind of DFS, but you always move along 
//         the border. One corner case is when moving up. You want to go up as far as possbile, so I added bool named up, to check
//          whether I should still go up or not. Without this, recursive call would go to the right at once (we don't want to
//          enable that).

// Worth to mention that I can make this function iterative and It would be more efficent, since recustive stack would
//    not be allocated, but it is easier to picture the problem on such recursive solution. 

// Time complexity: O(n^2)
// Space complexity: O(n^2)

// spiral_rec is a recursive function with 4 default parameters
void spiral_rec(int** res, int n, int c = 1, int i = 0, int j = 0, bool up = false) {
    if( i < 0 || i >= n || j < 0 || j >= n || res[i][j] != 0 ) return;
    res[i][j] = c;

    if( up ) spiral_rec(res, n, c+1, i-1, j, true);
    spiral_rec(res, n, c+1, i, j+1);
    spiral_rec(res, n, c+1, i+1, j);
    spiral_rec(res, n, c+1, i, j-1);
    spiral_rec(res, n, c+1, i-1, j, true);
}

int** CreateSpiralMatrix(int n) {
    // allocate dynamic 2d array for result (normaly it should be after deallocated, just to be clean :D)
    int** res = new int*[n]; // array of pointers
    for(int i=0; i<n; ++i) {
        res[i] = new int[n]; 
    }

    // initlizing matrix with 0, not to let it filled with some rubbish
    for(int i=0; i<n; ++i) {
        for(int j=0; j<n; ++j) {
            res[i][j] = 0;
        }
    }

    spiral_rec(res, n); // call for recursive function with 4 default arguments
    return res;
}
// ********************************************************

#include <iostream>
using namespace std;

int main() {
  
  // This is only simple example.
  // Solution must handle all cases.
  
  int n = 5;
  int** mat = CreateSpiralMatrix(n);
  
  if (mat)
  {
    for (int i=0; i<n; i++) { 
      for (int j=0; j<n; j++)
        cout << mat[i][j] << " ";
      cout << endl;
    }
  }
  else
      cout << "Matrix not initalized!" << endl;

  return 0;
}
