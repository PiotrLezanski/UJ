// package prm2t.lab4;

import java.util.Scanner;
import java.util.Arrays;

public class stableSelectionSort {
    public void showStableSelectionSort() {
        System.out.println();
        int iterCount = 0;
        int swapTmp;
        int[] swapTmp2D;
        int[] arrayRef = {4, 5, 3, 2, 4, 1};
        int[][] arrayRef2D = {{4, 0}, {5, 1}, {3, 2}, {2, 3}, {4, 4}, {1, 5}};
        for (int[] pair: arrayRef2D) {
            System.out.print(Arrays.toString(pair)+" ");
        }
        System.out.println();
        int arrayLength = arrayRef.length;
        int min;
        for (int i = 0; i < arrayLength-1; ++i) {
            min = i;
            for (int j = i+1; j < arrayLength; ++j) {
                if (arrayRef[j] < arrayRef[min]){
                    min = j;
                }
                ++iterCount;
            }
            swapTmp = arrayRef[min];
            swapTmp2D = arrayRef2D[min];
            while(min > i){
                // Przesuwam element z poprzedniej pozycji na pozycję znalezionego min, a indeks min zmiejszam o jeden.
                arrayRef[min] = arrayRef[min-1];
                arrayRef2D[min] = arrayRef2D[min-1];
                --min;
            }
            arrayRef[i] = swapTmp;
            arrayRef2D[i] = swapTmp2D;
            for (int[] pair: arrayRef2D) {
                System.out.print(Arrays.toString(pair)+" ");
            }
            System.out.println();
        }
        System.out.println("Po wszystkich (" + iterCount + ") iteracjach:");
        for (int[] pair: arrayRef2D) {
            System.out.print(Arrays.toString(pair)+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
        stableSelectionSort sel = new stableSelectionSort();
        sel.showStableSelectionSort();

    }
}

// /Users/piotr/Desktop/UJ/C

