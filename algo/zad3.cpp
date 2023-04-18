/******

There is a 3D point and a line segment (bounded 3D line) given by endpoints.
Write a method that will compute the distance from the point to the segment.
Consider all possible locations of the point in relation to the segment.
Handle all boundary cases.

All vector operations are available from included Eigen library.
Documentation: https://eigen.tuxfamily.org/dox/group__QuickRefPage.html

******/

using Eigen::Vector3d;

// https://eigen.tuxfamily.org/dox/group__QuickRefPage.html

// Method calculates a distance from point p to segment ab
// a - coordinates of the first point of the segment
// b - coordinates of the second point of the segment
// p - coordinates of the point to calculate distance to the segment from
// Returns distance
double Distance(const Vector3d & a, const Vector3d & b, const Vector3d & p) {
  // ************* YOUR CODE HERE *************
  
  
  
  return -1;
  // ******************************************
}

#include <iostream>
using namespace std;

int main() {
  
  // This is only simple example.
  // Solution must handle all cases.
  
  Vector3d a(0,0,0), b(1,0,0), p(1,1,0);
  cout << "Distance = " << Distance(a, b, p) << endl;
  return 0;
}

