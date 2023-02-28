import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import java.util.Arrays;

public class Test {
    public static int size;
    public static void main(String[] args) {

        int[] arr = { 10, 7, 8, 11, 9, 1, -5, 2, -1, 10 }; 
        size = arr.length;

        System.out.println(Arrays.toString(arr));
        heapsort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heapsort(int[] arr) {
        for(int i=(size/2)-1; i>=0; --i) {
            hepifyDown(arr, i);
        }

        while( size > 1 ) {
            deleteMax(arr);
        }
    }

    public static void deleteMax(int[] arr) {
        swap(arr, 0, size-1);
        --size;
        hepifyDown(arr, 0);
    }

    public static void hepifyDown(int[] arr, int i) {
        while( i < size / 2 ) {
            int max = i;
            int l = (2*i) + 1;
            int r = (2*i) + 2;
            if( l < size && arr[l] > arr[max] ) {
                max = l;
            }
            if( r < size && arr[r] > arr[max] ) {
                max = r;
            }
            if( i != max ) {
                swap(arr, i, max);
                i = max;
            }
            else break;
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}