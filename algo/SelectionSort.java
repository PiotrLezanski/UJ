class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {4, 5, 7, -8, 1, -2, 3};
        selection_sort(arr);

        // writing out sorted array
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void selection_sort(int[] arr) {
        int min = 0;
        for(int i=0; i<arr.length; ++i) {
            int min_index = i;
            int j = i + 1;
            for(; j < arr.length; ++j) {
                if( arr[j] < arr[min_index] ) {
                    min_index = j;
                }
            }
            // swap
            int tmp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = tmp;
        }
    }
}


