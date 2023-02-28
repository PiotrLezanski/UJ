
public class Boyer_Moore_Voting_alg {
    public static void main(String[] args) {
        int[] arr = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(arr));
    }

    public static int majorityElement(int[] arr) {
        int count = 0;
        int candidate = -1;
        for(int num : arr) {
            if( count == 0 ) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}

