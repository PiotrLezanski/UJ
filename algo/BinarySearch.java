class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2,3,4,6,9,12,15,11,8,6,4,1};
        // System.out.println(binarySearch2(arr, 5));
        System.out.println(binarySearch3(arr));
    }

    // checking if array contains an element
    public static boolean binarySearch_bool(int[] arr, int num) {
        // the array needs to be sorted
        int left = 0;
        int right = arr.length - 1;
        int index;
        
        while( left <= right ) {
            index = left + (right - left)/2;
            if( num > arr[index] ) {
                left = index + 1;
            }
            else if( num < arr[index] ) {
                right = index - 1;
            }
            else return true;
        }
        return false;
    }

    public int binarySearch_index(int lookingFor, int arrayIndex) {
        int left = 0;
        int right = arrList.get(arrayIndex).length - 1;
        bubbleSort(arrayIndex);
        int mid;
        int[] array = arrList.get(arrayIndex);

        while( left <= right ) {
            mid = left + (right - left)/2;
            if( lookingFor > array[mid] ) {
                left = mid + 1;
            }
            else if( lookingFor < array[mid] ) {
                right = mid - 1;
            }
            else return mid;
        }
        return -1;
    }

    // finding first number that is greater or equal given element
    public static int binarySearch2(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;
        int index;
        int ans = -1;
        while( left <= right ) {
            index = left + (right - left)/2;
            if( arr[index] >= num ) {
                ans = arr[index];
                right = index - 1;
            }
            else { // arr[index] < num
                left = index + 1;
            }
        }
        return ans;
    }

    // finding maximum in an increasing - decreasing array
    public static int binarySearch3(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int max = 0;
        int index;

        while( left <= right ) {
            index = left + (right - left)/2;
            if( index == 0 || arr[index - 1] < arr[index] ) {
                max = arr[index];
                left = index + 1;
            }
            else {
                right = index - 1;
            }
        }
        return max;
    }

    // find first occurrence
    public int binarySearch_first(int lookingFor, int arrayIndex) {
        int left = 0;
        int right = arrList.get(arrayIndex).length - 1;
        int result = -1;
        int mid;
        int[] array = arrList.get(arrayIndex);

        bubbleSort(arrayIndex);
        while( left <= right ) {
            mid = left + (right - left)/2;
            if( lookingFor > array[mid] ) {
                left = mid + 1;
            }
            else if( lookingFor < array[mid] ) {
                right = mid - 1;
            }
            else { // ==
                result = mid;
                right = mid - 1;
            }
        }
        return result;
    }

    // find last occurrence
    public int binarySearch_last(int lookingFor, int arrayIndex) {
        int left = 0;
        int right = arrList.get(arrayIndex).length;
        int result = -1;
        int mid;
        int[] array = arrList.get(arrayIndex);

        bubbleSort(arrayIndex);
        while( left <= right ) {
            mid = left + (right - left)/2;
            if( lookingFor > array[mid] ) {
                left = mid + 1;
            }
            else if( lookingFor < array[mid] ) {
                right = mid - 1;
            }
            else { // ==  
                result = mid;
                left = mid + 1;
            }
        }
        return result;
    }

    // number of elements with given value using binary search
    public int numOfElements(int element, int arrayIndex) {
        int first = binarySearch_first(element, arrayIndex);
        int last = binarySearch_last(element, arrayIndex);
        return last - first + 1;
    }

    // binary search using recursion
    public static int recBinary(int[] arr, int num, int left, int right) {
        if( left <= right ) {
            int mid = left + (right - left)/2;
            if( arr[mid] < num ) {
                return recBinary(arr, num, mid + 1, right);
            }
            else if( arr[mid] > num ) {
                return recBinary(arr, num, left, mid - 1);
            }
            else return mid;
        }
        return -1;
    }
}

// 2 3 4 6 9 12 11 8 6 4 1