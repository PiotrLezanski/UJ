
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class MyDate {
    int day;
    int month;
    int year;

    MyDate(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
    }

    public String toString() {
        return "{day=" + day + ", month=" + month + ", year=" + year + "}";
    }
}


public class Test {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<MyDate> list = new ArrayList<>();
        int size = sc.nextInt();
        for(int i=0; i<size; ++i) {
            int day = sc.nextInt();
            int month = sc.nextInt();
            int year = sc.nextInt();
            MyDate date = new MyDate(day, month, year);
            list.add(date);
        }

    }   

    void countingSort(int array[], int size, int place) {
        int[] output = new int[size + 1];
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
            max = array[i];
        }
        int[] count = new int[max + 1];

        for (int i = 0; i < max; ++i)
            count[i] = 0;

        // Calculate count of elements
        for (int i = 0; i < size; i++)
            count[(array[i] / place) % 10]++;

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--) {
            output[count[(array[i] / place) % 10] - 1] = array[i];
            count[(array[i] / place) % 10]--;
        }

        for (int i = 0; i < size; i++)
            array[i] = output[i];
    }

    // Function to get the largest element from an array
    int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++)
            if (array[i] > max)
            max = array[i];
        return max;
    }

    // Main function to implement radix sort
    void radixSort(int array[], int size) {
        // Get maximum element
        int max = getMax(array, size);

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10)
            countingSort(array, size, place);
    }
}
