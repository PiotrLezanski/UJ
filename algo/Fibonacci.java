// package prm2t.lab4;

import java.util.Scanner;



public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib_rec(5));
        System.out.println(iter_fib(5));

        int[] arr = new int[30];
        System.out.println(fib_mem(5, arr));
    }

    public static int fib_rec(int n) {
        if( n < 2 ) return n;
        else return fib_rec(n-1) + fib_rec(n-2);
    }
    
    public static int fib_mem(int n, int[] arr) {
        if( n <= 0 ) return 0;
        else if( n == 1 ) return 1;
        else if( arr[n] != 0 ) {
            return arr[n];
        }
        else {
            arr[n] = fib_mem(n-1, arr) + fib_mem(n-2, arr);
            return arr[n];
        }
    }

    public static int iter_fib(int n) {
        int a = 0;
        int b = 1;
        int tmp;
        for(int i=0; i<n; i++) {
            tmp = a;
            a = a + b;
            b = tmp;
        }
        return a;
    }
}


