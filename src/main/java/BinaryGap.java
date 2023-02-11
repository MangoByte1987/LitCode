//A binary gap within a positive integer N is
// any maximal sequence of consecutive zeros
// that is surrounded by ones at both ends in the binary representation of N.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryGap {
    public static void main(String [] args){
        System.out.println("BinaryGap sol1: " +solution(1041));
        System.out.println("BinaryGap sol2 : " +solution2(1041));
        System.out.println("BinaryGap sol1 : " +solution(15));
        System.out.println("BinaryGap sol2 : " +solution2(15));
        System.out.println("BinaryGap sol1 : " +solution(6));
        System.out.println("BinaryGap sol2 : " +solution2(6));
    }
    public static int solution2(int N){
        String binaryN = Integer.toBinaryString(N);
       // System.out.println("BinaryN : "+binaryN);
        int zeroCounter=0;
        List<Integer> zeroCounterList = new ArrayList<>();
        for(int i =0;i<binaryN.length();i++){
            if(binaryN.charAt(i) == '0'){
                    zeroCounter++;
            } else{
                zeroCounterList.add(zeroCounter);
                zeroCounter = 0;
            }
        }
        return Collections.max(zeroCounterList);
    }
    public static int solution(int N) {
        // write your code in Java SE 8
        String binaryN = Integer.toBinaryString(N);
        System.out.println("BinaryN : "+binaryN);
        int max_gap = 0;
        int current_gap =0;
        boolean counting = false;

        // Using the "concept of bit manipulation" and "& operation"

        while( N !=0 ){

            if(counting == false && (N&1) == 1){    // for the first "1"
                    counting = true;  // start to count
            }
            else{                    // counting = true
                if(counting == true && ( N&1) ==0){      // note: cannot use n&1 withoug "()"
                    current_gap ++;
                }
                else{ // N & 1 == 1
                    max_gap = Math.max(max_gap, current_gap);
                    current_gap = 0; // reset
                }
            }

            N = N >> 1; // shift by one (right side)
            // note: cannot just write "N >> 1"

           // System.out.println("N : " + N);
        }

        return max_gap;
    }
}
