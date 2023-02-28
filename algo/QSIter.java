import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;    
import java.util.Vector;

public class QSIter {
    public static void main(String[] args) {
        
        int arr[] = { 4, 5, 0, 2, 1, -1, 3, 2, 3 };
        quicksort_iter(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void quicksort_iter(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        Stack<Integer> stack = new Stack<>();

        stack.push(l);
        stack.push(r);
        while( !stack.isEmpty() ) {
            r = stack.pop(); // RIGHT IS ON THE TOP, SO ORDER OF POPPING MATTERS!
            l = stack.pop();

            int pivot = partition(arr, l, r);
            if( pivot - 1 > l ) {
                stack.push(l);
                stack.push(pivot - 1);
            }
            if( pivot + 1 < r ) {
                stack.push(pivot + 1);
                stack.push(r);
            }
        }
    }

    public static int partition(int[] arr, int begin, int end) {
        int i = begin - 1;
        int j = begin;
        int pivot = arr[end];

        for(; j<end; ++j) {
            if( arr[j] < pivot ) {
                ++i;
                swap(arr, i, j);
            }
        }
        ++i;
        swap(arr, i, j);
        return i;
    }

    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}

