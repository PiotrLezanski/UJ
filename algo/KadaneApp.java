class KadaneApp {
    public static void main(String[] args) {
        
        int[] arr = {9,-5,6,-10, 4,-1,-10};

        int beg = 0;
        int end = 0;
        int tmp_beg = 0;

        int curr_sum = 0;
        int max_sum = 0;

        for(int i=0; i<arr.length; ++i) {
            curr_sum += arr[i];
            if( curr_sum < 0 ) {
                tmp_beg = i+1;
                curr_sum = 0;
            }
            else if( curr_sum > max_sum ) {
                max_sum = curr_sum;
                beg = tmp_beg;
                end = i;
            }
        }

        System.out.println("START: " + beg);
        System.out.println("END: " + end);
        System.out.println("SUM: " + max_sum);
    }
}