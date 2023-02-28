import java.util.Scanner;

class MaxHeap {
    int[] heapArr; 
    int n; 

    MaxHeap(int[] arr) {
        heapArr = arr;
        n = arr.length;
    }

    void heapifyDown(int i) {
        int max = i;
        int l = (2 * i) + 1;
        int r = (2 * i) + 2;

        if( l < n && heapArr[l] > heapArr[max] ) {
            max = l;
        }
        if( r < n && heapArr[r] > heapArr[max] ) {
            max = r;
        }

        if( max != i ) {
            int tmp = heapArr[i];
            heapArr[i] = heapArr[max];
            heapArr[max] = tmp;
            heapifyDown(max);
        }
    }


    void heapifyDown_iterative(int i) {
        int max = i;
        
        while( i < n / 2 ) {
            int l = (2*i) + 1;
            int r = (2*i) + 2;
            
            if( l < n && heapArr[l] > heapArr[max] ) {
                max = l;
            }
            if( r < n && heapArr[r] > heapArr[max] ) {
                max = r;
            }

            if( max != i ) {
                int tmp = heapArr[i];
                heapArr[i] = heapArr[max];
                heapArr[max] = tmp;
                i = max;
            }
            else {
                break;
            }
        }
    }

    int extractMax() {
        int max = heapArr[0];
        heapArr[0] = heapArr[n-1];
        --n;
        heapifyDown_iterative(0);
        return max;
    }

    void insert(int x) {
        int[] new_arr = new int[n+1];
        System.arraycopy(heapArr, 0, new_arr, 0, n);
        heapArr = new_arr;
        heapArr[n] = x;
        heapifyUp(n);
        ++n;
    }

    void heapifyUp_iterative(int i) {
        while( i > 0 ) {
            int parent = (i-1) / 2;
            if( heapArr[parent] < heapArr[i] ) {
                int tmp = heapArr[parent];
                heapArr[parent] = heapArr[i];
                heapArr[i] = tmp;
                i = parent;
            }
            else break;
        }
    }

    void heapifyUp(int i) {
        int parent = (i-1) / 2;

        if( heapArr[parent] < heapArr[i] ) {
            int tmp = heapArr[parent];
            heapArr[parent] = heapArr[i];
            heapArr[i] = tmp;
            heapifyUp(parent);
        }
    }

    void printHeap() {
        System.out.print("HEAP: ");
        for(int i=0; i<heapArr.length; i++) {
            System.out.print(heapArr[i] + " ");
        }
        System.out.println();
    }

    void heapSort() {
        while( n > 1 ) {
            heapArr[n-1] = extractMax();
        }
    }
} 

// buildHeap - i = (len/2)-1;
// heapifyUp - parent = (len-1)/2;

class Heaps {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {

        int[] arr = {5,6,4,3,21,6,1,9,6};
        MaxHeap heap = buildHeap(arr);
        heap.heapSort();
        heap.printHeap();
        
    }

    public static MaxHeap buildHeap(int[] arr) {
        MaxHeap heap = new MaxHeap(arr);
        for(int i = (arr.length-1)/2; i >= 0; --i) {
            heap.heapifyDown_iterative(i);
            // heap.heapifyDown(i);
        }
        return heap;
    }
}