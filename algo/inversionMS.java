import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class inversionMS {
    public static void main(String[] args) {
        
        int[] arr = {1,3,2,3,1};
        System.out.println(inversion(arr, 0, arr.length - 1));
        System.out.println(inversion_iter(arr));

    }

    public static int inversion(int[] arr, int l, int r) {
        int inv = 0;
        if( l < r ) {
            int mid = (l+r)/2;
            inv += inversion(arr, l, mid);
            inv += inversion(arr, mid+1, r);
            inv += merge(arr, l, mid, r);
        }
        return inv;
    }

    public static int inversion_iter(int[] arr) {
        int n = arr.length;
        int inv = 0;
        for(int curr_size=1; curr_size<n; curr_size*=2) {
            for(int left_start=0; left_start<n-1; left_start+=(2*curr_size)) {
                int mid = Math.min(left_start + curr_size - 1, n-1);
                int right = Math.min(left_start + 2*curr_size - 1, n-1);
                inv += merge(arr, left_start, mid, right);
            }
        }
        return inv;
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int s1 = mid - left + 1;
        int s2 = right - mid;
        int[] leftArr = new int[s1];
        int[] rightArr = new int[s2];
        int l = 0;
        int r = 0;
        for(; l<s1; ++l) {
            leftArr[l] = arr[left + l];
        }
        for(; r<s2; ++r) {
            rightArr[r] = arr[mid + 1 + r];
        }

        l = 0;
        r = 0;
        int i = left;
        int inv = 0;
        while( l < s1 && r < s2 ) {
            if( leftArr[l] <= rightArr[r] ) {
                arr[i++] = leftArr[l++];
            }
            else {
                arr[i++] = rightArr[r++];
                inv += (mid + 1) - (left + l);
            }
        }
        while( l < s1 ) {
            arr[i++] = leftArr[l++];
        }
        while( r < s2 ) {
            arr[i++] = rightArr[r++];
        }
        
        return inv;
    }
}






