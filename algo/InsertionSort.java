import java.util.Arrays;
import java.util.Scanner;


class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, -6, 1, 9, 12, 2};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // insertion sort is stable as nature (similarly QuickSort, Merge Sort, Bubble Sort)
    public static void insertionSort(int[] arr) {
        int size = arr.length;
        int j;
        int value;

        for(int i=1; i<size; ++i) {
            j = i - 1;
            value = arr[i];
            while( j >= 0 && arr[j] > value ) {
                arr[j+1] = arr[j];
                j -= 1;
            }
            arr[j+1] = value;
        }
    }
    /*
    selection sort is unstable, because you are finding minimum element in unsorted array and 
        placing it at the proper possition

    selection sort can be refreamed to be stable, by insted of swaping elements, shift them, as 
        in insertion sort
    */
}
