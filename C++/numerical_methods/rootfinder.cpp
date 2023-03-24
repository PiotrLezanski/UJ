// Piotr Lezanski
#include <iostream>
#include <math.h>
#include <iomanip> 
using namespace std;

double wielomian(double x){ 	return (((x-6)*x+11)*x)-6;	}
double wielomianSinExp(double x) {	return ((((x-6)*x+11)*x)-4 + sin(15*x))*exp(-x*x);	}
double kwadrat(double x){	return (x*x-2);	 }
double kwadrat100(double x){  return 1e100*(x*x-2);	}
double kwadrat_10(double x){ 	return 1e-10*(x*x-2);	}

int sgn(double x) {
    return x>=0 ? 1 : 0;
}

// function that effectively implement bisection and secant method, to find root of given function
double findZero(double (*f)(double), double a, double b, int M, double eps, double delta) {
    double x0 = b;
    double fa = f(a);
    double fb = f(b);
    double fx0 = fb;
    double e = b - a;
    if( std::abs(fa) < eps ) return a;
    if( std::abs(fb) < eps ) return b;
    M -= 2;

    // bisection method
    if( sgn(fa) != sgn(fb) ) {
        while( M > 0 ) {
            e = e / 2;
            x0 = a + e;
            fx0 = f(x0);
            --M;
            if( std::abs(e) < delta || std::abs(fx0) < eps ) 
                return x0;
            else if( std::abs(e) < 0.1 )
                break;

            if( fx0 == 0 ) {
                return x0;
            }
            if( sgn(fa) != sgn(fx0) ) {
                b = x0;
                fb = fx0;
            }
            else {
                a = x0;
                fa = fx0;
            }
        }
    }

    b = x0;
    fb = fx0;
    // secant method
    while( M > 0 ) {
        x0 = b - fb * (b-a) / (fb - fa);
        fx0 = f(x0);
        --M;
        if( std::abs(fx0) < eps || std::abs(b - x0) < delta )
            return x0;
        
        a = b;
        fa = fb;

        b = x0;
        fb = fx0;
    }

    return b;
}

int main() 
{
    cout.precision(17);                                                
	cout << left << setw(22) << findZero(wielomian, 0, 4, 20, 1e-15, 1e-14) << "1,2,3" << endl; 
	cout << left << setw(22) << findZero(wielomian, 0, 40, 20, 1e-15, 1e-14) << "1,2,3" << endl;
	cout << left << setw(22) << findZero(wielomian, 1, 2, 2, 1e-15, 1e-14) << "1,2" << endl;    
	cout << left << setw(22) << findZero(wielomian, -150, 1.9, 20, 1e-15, 1e-14) << "1" << endl;
	cout << left << setw(22) << findZero(wielomian, 1.5, 2.99, 20, 1e-15, 1e-14) << "2" << endl;
	cout << left << setw(22) << findZero(wielomian, 2.01, 40, 20, 1e-15, 1e-14) << "3" << endl; 
	cout << left << setw(22) << findZero(wielomian, 1.5, 6, 20, 1e-15, 1e-14) << "1,2,3" << endl;

	cout << left << setw(22) << findZero(wielomianSinExp, -1, 3, 60, 1e-60, 1e-14) << "0.43636925909804245" << endl;  
	cout << left << setw(22) << findZero(wielomianSinExp, -3, 3, 60, 1e-160, 1e-14) << "0.43636925909804245" << endl; 
	
	cout << left << setw(22) << findZero(kwadrat, 0, 4, 15, 1e-11, 1e-14) << "1.414213562373095" << endl;     
	cout << left << setw(22) << findZero(kwadrat100, 0, 4, 15, 1e-11, 1e-14) << "1.414213562373095" << endl;  
	cout << left << setw(22) << findZero(kwadrat_10, 0, 4, 10, 1e-10, 1e-14) << "[1, 1.73205]" << endl;       
 	cout << left << setw(22) << findZero(kwadrat_10, 0, 4, 15, 1e-160, 1e-14) << "1.414213562373095" << endl;     
    
    return 0;
}
