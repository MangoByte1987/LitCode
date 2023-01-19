import java.util.Arrays;

public class FindMedian {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double[] arr = new double[nums1.length + nums2.length];

        for (int j=0; j<nums1.length; j++) {
            arr[j] = nums1[j];
        }
        for (int k=0; k<nums2.length; k++) {
            arr[nums1.length+k] = nums2[k];
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;
        int mid = start + (end-start)/2;
        System.out.println("start :" + start);
        System.out.println("end :" + end);
        System.out.println("mid :" + mid);
        if (arr.length%2==1) {
            return arr[mid];
        }
        else {
            double med = (arr[mid] + arr[mid+1])/2;
            return med;
        }
    }

    public static double findMedianSortedArraysBS(int[] nums1, int[] nums2) {

        // Approach : Binary Search - Logic of partitioning only for this problem; TC : O(log(m+n))
        int n1 = nums1.length;
        int n2 = nums2.length;
        if(n1>n2)
            return findMedianSortedArraysBS(nums2, nums1);

        //Partionning logic starts
        int low = 0, high = n1; // we always run binary search on smaller array
        while(low<=high){
            int partX = low+(high-low)/2; //mid of num 1
            int partY = (n1+n2+1)/2 - partX; //mid of merged array - mid of num1

            //If extreme left partition before 0th index then take -infinity or else take nums1[partX-1]
            double l1 = partX == 0 ? Integer.MIN_VALUE : nums1[partX-1];
            double R1 = partX == n1? Integer.MAX_VALUE : nums1[partX];

            double l2 = partY == 0 ? Integer.MIN_VALUE : nums2[partY-1];
            double R2 = partY == n2? Integer.MAX_VALUE : nums2[partY];

            if(l1<=R2 && l2<=R1){ // correct partition; satisfies
                if((n1+n2) % 2 == 0 ){
                    return (Math.max(l1,l2) + Math.min(R1,R2))/2;
                }else
                    return Math.max(l1,l2);
            }else if(l2>R1)
                low = partX+1;
            else
                high = partX-1;
        }
        return -1;
    }

    public static void main(String args []){
        int num1 [] = {1,2,3};
        int num2 [] = {4,5,6};
        double result = findMedianSortedArraysBS(num1,num2);
        System.out.println("result : " + result);
    }

}
