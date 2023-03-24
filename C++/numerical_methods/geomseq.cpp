// Piotr Lezanski
#include <iostream>
#include <math.h>

bool roots(float a, float b, float c, float* x1, float* x2) {
    float det = b*b - 4*a*c;
    if (det > 0) {
        if(b >= 0) {
            *x1 = (-b - sqrt(det)) / (2 * a);
            *x2 = (2 * c) / (-b - sqrt(det));
        }
        else {
            *x1 = (2 * c) / (-b + sqrt(det));
            *x2 = (-b + sqrt(det)) / (2 * a);
        }
    }
    else if (det == 0) {
        *x1 = -b/(2*a);
        *x2 = *x1;
    }
    else {
        return false;
    }
    return true;
}

void printErr() {
    float x = 0;
    std::cout<< x << " " << x << " " << x <<std::endl;
}

int main() 
{
    int n;
    std::cin>> n;
    while( n-- ) {
        float prod;
        float sum;

        std::cout.precision(10);
        std::cout<<std::scientific;

        std::cin>> prod>> sum;

        float sqrt_p = cbrt(prod);

        float a1;
        float a2;
        float a;
        float q1; 
        float q2; 
        float q;

        if( prod == 0 || !roots(1, (sqrt_p/sqrt_p) - (sum/sqrt_p), 1, &q1, &q2) ) {
            printErr();
            continue;
        }

        a1 = sum / (1 + q1 + q1*q1);
        a2 = sum / (1 + q2 + q2*q2);

        if( a1 >= a1 * q1 * q1 ) {
            a = a1;
            q = q1;
        }
        else if( a2 >= a2 * q2 * q2 ) {
            a = a2;
            q = q2;
        }
        else {
            printErr();
            continue;
        }
        std::cout<< a << " " << a*q << " "<< a*q*q <<std::endl;
    }
    return 0;
}
