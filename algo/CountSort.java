
import java.util.Scanner;
import java.util.Arrays;

public class Test {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.print("Size: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        for(int i=0; i<size; ++i) {
            arr[i] = sc.nextInt();
        }

        countSort(arr);
        System.out.println(Arrays.toString(arr));
    }   

    public static void countSort(int[] arr) {
        int[] count = new int[findMax(arr)+1];
        int[] output = new int[arr.length];

        for(int i=0; i<count.length; ++i) {
            count[i] = 0;
        }

        for(int i=0; i<arr.length; ++i) {
            ++count[arr[i]];
        }

        for(int i=1; i<count.length; ++i) {
            count[i] += count[i-1];
        }

        for(int i=arr.length-1; i>=0; --i) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        for(int i=0; i<arr.length; ++i) {
            arr[i] = output[i];
        }
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for(int i = 1; i<arr.length; ++i) {
            if( arr[i] > max ) {
                max = arr[i];
            }
        }
        return max;
    }
}
