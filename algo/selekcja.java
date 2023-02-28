import java.util.Scanner;

public class Test {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        int[] arr = {7, 10, 4, 3, 20, 15, 11, 8}; // k = 3 -> 7

        int k = 3;
        System.out.println(select(arr, k-1, 0, arr.length));
    }

    public static int select(int[] arr, int k, int l, int r) {
        if( r - l <= 1 ) {
            return arr[l];
        }

        int medians = 0;
        for(int i=l; i<r; i += 5) {
            int n = i+5 < r ? i+5 : r;
            insertionSort(arr, i, n);
            swap(arr, l+medians, (i+n)/2);
            ++medians;
        }
        // printArr(arr);

        int M = select(arr, (medians+1)/2, l, l+medians);

        partition(arr, M, l, r);

        int i = l;
        while( arr[i] < M ) i++;
        int s1 = i - 1;

        while( i < r && arr[i] == M ) i++;
        int s2 = i - 1;

        if( k <= s1 - l ) return select(arr, k, l, s1+1);
        else if( k <= s2 - l ) return M;
        else return select(arr, k-s2+l-1, s2+1, r);
    }

    public static void partition(int[] arr, int key, int left, int right) {
        int l = left;
        int r = right - 1;
        int i = left;
        while( i <= r ) {
            if( arr[i] < key ) {
                swap(arr, i, l);
                l++;
                i++;
            }
            else if( arr[i] > key ) {
                swap(arr, i, r);
                --r;
            }
            else {
                ++i;
            }
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void insertionSort(int[] arr, int s, int e) {
        for(int i=s+1; i < e; ++i) {
            int key = arr[i];
            int j = i - 1;
            while( j >= s && arr[j] > key ) {
                arr[j+1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }
    }

    public static void printArr(int[] arr) {
        for(int i=0; i<arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}