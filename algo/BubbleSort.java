class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,2, -5, -13, 5, 5, 7,1};
        bubble_sort(arr, arr.length);
    }

    public static void bubble_sort(int[] arr, int n) {
        System.out.println(arr);
        for(int j=0; j<n-1; j++) {
            for(int i=0; i<n-1; i++) {
                if( arr[i] > arr[i+1] ) {
                    int tmp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = tmp;
                }
            }
        }

        // writing out sorted array
        for(int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}