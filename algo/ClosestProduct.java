import java.util.*;

public class ClosestProduct {
    public static void main(String[] args) {
        
        int[] arr = {7,4,3,1};
        quicksort(arr, 0, arr.length-1);
        closestProduct(arr, 8);

    }

    public static void closestProduct(int[] arr, int target) {
        int min_value = Integer.MAX_VALUE;
        int tmp_value;
        int ind1 = 0;
        int ind2 = 0;

        int right = arr.length-1;
        for(int i=0; i<arr.length; ++i) {
            int left = i;
            
            while(left <= right) {
                int mid = left + (right - left)/2;
                if( i != mid && arr[i] * arr[mid] < target ) {
                    tmp_value = target - (arr[i] * arr[mid]);
                    if( tmp_value < min_value ) {
                        min_value = tmp_value;
                        ind1 = i;
                        ind2 = mid;
                    }
                    left = mid + 1;
                }
                else if( i != mid && arr[i] * arr[mid] > target ) {
                    tmp_value = (arr[left] * arr[mid]) - target;
                    if( tmp_value < min_value ) {
                        min_value = tmp_value;
                        ind1 = i;
                        ind2 = mid;
                    }
                    right = mid - 1;
                }
                else {
                    if( i != mid ) {
                        ind1 = i;
                        ind2 = mid;
                        break;
                    }
                    else {
                        left = mid + 1;
                        // continue;
                    }
                }
            }
        }

        System.out.println(arr[ind1] + " " + arr[ind2]);
    }

// sort
    public static void quicksort(int[] arr, int begin, int end) {
        if( begin < end ) {
            int pivot = partition(arr, begin, end);
            quicksort(arr, begin, pivot-1);
            quicksort(arr, pivot+1, end);
        }
    }

    public static int partition(int[] arr, int begin, int end) {
        int i = begin-1;
        int j = begin;
        int pivot = arr[end];
        for(; j<end; ++j) {
            if( arr[j] < pivot ) {
                ++i;
                swap(arr, i, j);
            }
        }
        ++i;
        swap(arr, i, end);
        return i;
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
// end sort
}