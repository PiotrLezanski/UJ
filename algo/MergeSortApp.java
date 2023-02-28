
import java.util.Scanner;

import javax.swing.RowFilter;

import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class MergeSortApp {
    public static void main(String[] args) {
        
        int[] arr = { 10, 7, 8, 11, 9, 1, -5, 2, -1, 10 }; 

        System.out.println(Arrays.toString(arr));
        // mergeSort(arr);
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if( left >= right ) return;
        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        int[] arr2 = new int[arr.length];
        for(int i=left; i<=right; ++i) { // przepisanie wartosci z tablicy 1. do 2.
            arr2[i] = arr[i];
        }
        
        int i = left;       // poczatek 1. podtablicy
        int j = middle + 1; // poczatek 2. podtablicy
        int k = left;       // indeks w tablicy wynikowej

        while( i <= middle && j <= right ) {
            if( arr2[i] <= arr2[j] ) { // "<=" zatem sortowanie stabilne
                arr[k++] = arr2[i++];
            }
            else {
                arr[k++] = arr2[j++];
            }
        }

        while( i <= middle ) {
            arr[k++] = arr2[i++];
        }
        while( j <= right ) {
            arr[k++] = arr2[j++];
        }
    }


/*
    public static void mergeSort(int[] arr) {
        int len = arr.length;
        if( len <= 1 ) return;

        int middle = len / 2;
        int[] left = new int[middle];
        int[] right = new int[len - middle];

        int l = 0;
        int r = 0;
        for(int i=0; i<len; ++i) {
            if( i < middle ) left[l++] = arr[i];
            else right[r++] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }


    public static void merge(int[] arr, int[] left, int[] right) {
        int len1 = left.length;
        int len2 = right.length;

        int i = 0; // 
        int l = 0; // indeks 
        int r = 0; // 

        while( l < len1 && r < len2 ) {
            if( left[l] <= right[r] ) {
                arr[i++] = left[l++];
            }
            else {
                arr[i++] = right[r++];
            }
        }
        while( l < len1 ) arr[i++] = left[l++];
        while( r < len2 ) arr[i++] = right[r++];
    }
*/
}

