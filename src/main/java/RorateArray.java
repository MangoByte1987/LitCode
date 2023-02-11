import java.util.HashMap;
import java.util.Map;

public class RorateArray {
    public static void main(String [] args){
        int [] A = {1,2,3,4,5,6,7};
        int K = 1;
        int [] result = rotateArray(A,K);
        for(int i : result)
            System.out.print(i);
    }
    private static int [] rotateArray(int [] A, int K){
        int [] rotatedArr = new int [A.length];
        for(int i =0;i<A.length;i++){
            int index= (i+K) % A.length;
            rotatedArr[index] = A[i];
        }
        return rotatedArr;
    }
}
