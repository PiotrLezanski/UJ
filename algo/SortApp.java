// Piotr Lezanski 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class List {
    ArrayList<int[]> arrList;
    int size;
    List() {
        size = 0;
        arrList = new ArrayList<>();
    };

    public void addArray(int[] arr) {
        arrList.add(arr);
        size++;
    }

    public void display() {
        if( arrList.size() != 0 ) {
            int subSize;
            for(int i=0; i<size; i++) {
                subSize = arrList.get(i).length;
                System.out.println("=== "+ (i+1) + " ===");
                for(int j=0; j<subSize; j++) {
                    System.out.print(arrList.get(i)[j]+" ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("The list is empty");
        }
    }

    public void bubbleSort(int arrayIndex) {
        System.out.println("Bubble Sort... Array Index: "+ arrayIndex);
        if( arrayIndex < size ) {
            int n = arrList.get(arrayIndex).length;
            int[] array = arrList.get(arrayIndex);
            for(int i=0; i<n-1; i++) {
                for(int j=0; j<n-i-1; j++) {
                    // int[] tmp_arr = arrList.get(arrayIndex);
                    if( array[j] > array[j+1] ) {
                        int tmp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = tmp;
                    }
                }
            }
        }
    }

    public void selectionSort(int arrayIndex) {
        System.out.println("Selection Sort... Array Index: "+ arrayIndex);
        if( arrayIndex < size ) {
            int n = arrList.get(arrayIndex).length;
            int[] arr = arrList.get(arrayIndex);

            for(int i = 0; i < n; ++i) {
                int min_index = i;
                int j = i + 1;
                while( j < n ) {
                    if( arr[j] < arr[min_index] ) {
                        min_index = j;
                    }
                    ++j;
                }
                // swap
                int tmp = arr[i];
                arr[i] = arr[min_index];
                arr[min_index] = tmp;
            }
        }
    }

    public void selectionSort_stable(int arrayIndex) {
        System.out.println("Stable Selection Sort... Array Index: "+ arrayIndex);
        if( arrayIndex < size ) {
            int n = arrList.get(arrayIndex).length;
            int[] arr = arrList.get(arrayIndex);

            for(int i = 0; i < n; ++i) {
                int min_index = i;
                int j = i + 1;
                while( j < n ) {
                    if( arr[j] < arr[min_index] ) {
                        min_index = j;
                    }
                    ++j;
                }
                // shift
                int key = arr[min_index];
                while( min_index > i ) {
                    arr[min_index] = arr[min_index-1];
                    --min_index;
                }
                arr[i] = key;
            }
        }
    }

    public void insertionSort(int arrayIndex) {
        int[] arr = arrList.get(arrayIndex);
        int len = arr.length;
        int key;
        int j;
        for(int i=1; i < len; ++i) {
            key = arr[i];
            j = i - 1;
            while( j >= 0 && arr[j] > key ) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }
    }
}

class SortApp {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        List arrList = new List();
        System.out.print("Ile tablic: ");
        int num = sc.nextInt();
        int[] tmpArr;
        int size;
        for(int i=1; i<=num; i++) {
            System.out.print("Ile elementów dla "+ i +". tablicy: " );
            size = sc.nextInt();
            tmpArr = new int[size];
            System.out.print("Podaj elementy tablicy: ");
            for(int j=0; j<size; j++) {
                tmpArr[j] = sc.nextInt();
            }
            arrList.addArray(tmpArr);
        }
        arrList.display();
        // arrList.selectionSort_stable(0);
        // arrList.bubbleSort(0);
        arrList.insertionSort(0);
        arrList.display();
    }
}

    

