import java.util.Scanner;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        
        int[] arr = { 12, 7, 8, 9, 1, -5, 2, -1, 10 };
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void mergeSort(int[] arr) {
        int n = arr.length;
        for(int curr_size = 1; curr_size < n; curr_size *= 2) {
            for(int left_start = 0; left_start < n - 1; left_start += (2*curr_size)) {
                // finding ending point of left subarray, mid + 1 is starting point of right subarray
                int mid = min(left_start + curr_size - 1, n-1); 
                int right_end = min(left_start + 2*curr_size - 1, n-1);
                merge(arr, left_start, mid, right_end);
            }
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int s1 = mid - left + 1;
        int s2 = right - mid;
        int[] leftArr = new int[s1];
        int[] rightArr = new int[s2];
        
        int l = 0;
        int r = 0;
        for(; l < s1; ++l) {
            leftArr[l] = arr[left + l];
        }
        for(; r < s2; ++r) {
            rightArr[r] = arr[mid + 1 + r];
        }

        int i = left;
        l = 0;
        r = 0;
        while( l < s1 && r < s2 ) {
            if( leftArr[l] <= rightArr[r] ) {
                arr[i++] = leftArr[l++];
            }
            else {
                arr[i++] = rightArr[r++];
            }
        }
        
        while( l < s1 ) {
            arr[i++] = leftArr[l++];
        }
        while( r < s2 ) {
            arr[i++] = rightArr[r++];
        }
    }

    public static int min(int x, int y) {
        return x<y ? x : y;
    }
}

