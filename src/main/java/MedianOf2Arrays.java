import java.util.Arrays;

public class MedianOf2Arrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int [] temp = new int[nums1.length + nums2.length];

        for (int i =0 ,j=0;i< temp.length;i++){
            if(i < nums1.length){
                temp[i] = nums1[i];
            }
            else{
                temp[i] = nums2[j++];
            }
        }

        Arrays.sort(temp);

        if(temp.length % 2 ==0){
            return (temp[temp.length/2] + temp[(temp.length/2)-1]) / 2.00;
        }
        else{
            return temp[temp.length/2];
        }
    }

    public static void main(String [] args){
        int [] nums1 = {1,2};
        int [] nums2 = {3,4};
         double median = findMedianSortedArrays(nums1,nums2);
         System.out.println("Median of arrays is : "+median);
    }
}
