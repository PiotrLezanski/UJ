import java.util.Scanner;
import java.util.Arrays;

public class MSHalf {
    public static void main(String[] args) {
        
        int[] arr = { 12, 7, 8, 9, 1, -5, 2, -1, 10 };
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println();

        int[] arr2 = { 4, 5, 0, 2, 1, -1, 3, 2, 3 };
        System.out.println(Arrays.toString(arr2));
        mergeSort(arr2);
        System.out.println(Arrays.toString(arr2));
        System.out.println();

        int[] arr3 = { 35, 10, -4, 5, 67, 8, -1, 5, -4, 65, 4 };
        System.out.println(Arrays.toString(arr3));
        mergeSort(arr3);
        System.out.println(Arrays.toString(arr3));

    }

    public static void mergeSort(int[] arr) {
        int n = arr.length;
        for(int curr_size = 1; curr_size < n; curr_size *= 2) {
            for(int left_start = 0; left_start < n - 1; left_start += (2*curr_size)) {
                int mid = min(left_start + curr_size - 1, n-1); 
                int right_end = min(left_start + 2*curr_size - 1, n-1);
                merge(arr, left_start, mid, right_end);
            }
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int s1 = mid - left + 1;
        int[] leftArr = new int[s1];
        
        int l = 0;
        for(; l < s1; ++l) {
            leftArr[l] = arr[left + l];
        }

        int i = left;
        l = 0;
        int r = mid + 1;
        while( l < s1 && r <= right ) {
            if( leftArr[l] <= arr[r] ) {
                arr[i++] = leftArr[l++];
            }
            else {
                arr[i++] = arr[r++];
            }
        }
        
        while( l < s1 ) {
            arr[i++] = leftArr[l++];
        }
        while( r <= right ) {
            arr[i++] = arr[r++];
        }
    }

    public static int min(int x, int y) {
        return x<y ? x : y;
    }
}

