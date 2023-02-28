
import java.util.Scanner;
import java.sql.RowId;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

public class QuickSortApp {
    public static void main(String[] args) {
        
        int[] arr = { 10, 7, 8, 11, 9, 1, -5, 2, -1, 10 }; 
        int n = arr.length;    

        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, n - 1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int begin, int end) {
        if( begin < end ) {
            int pivot = partition(arr, begin, end);
            quickSort(arr, begin, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }

    public static int partition(int[] arr, int begin, int end) {
        int i = begin - 1;
        int j = begin;

        int pivot = arr[end];
        for(; j < end; ++j) {
            if( arr[j] < pivot ) {
                ++i;
                swap(arr, i, j);
            }
        }
        ++i;
        swap(arr, i, end);
        return i;
    }


    public static int partition2(int[] arr, int begin, int end) {
        int i = begin - 1;
        int j = end;
        int pivot = arr[end];
        while(true) {
            while( arr[++i] < pivot );
            while( j > begin && arr[--j] > pivot );
            if( i >= j ) break;
            else swap(arr, i, j);
        }
        swap(arr, i, end);
        return i;
    }



    /*
    public static void quickSort(int[] arr, int begin, int end) {
        if( begin < end ) {
            int pivot = partition2(arr, begin, end);
            quickSort(arr, begin, pivot-1);
            quickSort(arr, pivot+1, end);
        }
        else return;
    }

    //* VERSION 1
    public static int partition1(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        
        for(int j = start; j<end; j++) {
            if( arr[j] <= pivot ) {
                i++;
                swap(arr, i, j);
            }
        }
        i++;
        swap(arr, end, i);
        return i;
    }

    //* VERSION 2
    public static int partition2(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        int j = end;
        while(true) {
            while( arr[++i] < pivot );
            while( j >= start && arr[--j] > pivot );
            if( i >= j ) break;
            else swap(arr, i, j);
        }
        swap(arr, i, end);
        return i;
    }
    */

    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
    
}

