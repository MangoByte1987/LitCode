import java.util.Arrays;

/*
    An integer M and a non-empty array A consisting of N non-negative integers are given.
    All integers in array A are less than or equal to M.
    A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
    The slice consists of the elements A[P], A[P + 1], ..., A[Q].
    A distinct slice is a slice consisting of only unique numbers.
    That is, no individual number occurs more than once in the slice.
    For example, consider integer M = 6 and array A such that:

        A[0] = 3
        A[1] = 4
        A[2] = 5
        A[3] = 5
        A[4] = 2
        There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 */

// caterpiller pattern to solve

public class CountDistinctSlices {
    public static void main (String [] args){
        int [] A = {3,4,5,5,2};
        int M = 6;
        int slices = solution(M,A);
        System.out.println("DistinctSlices : " + slices);

    }
    public static int solution(int M, int[] A) {
        int slices = 0;
        int front = 0;
        int back = 0;
        int len = A.length;
        boolean visited [] = new boolean[M+1];
        //Arrays.fill(visited,false);

        while(front < len && back < len){
            if(visited[A[front]] == false){
                slices = slices + (front-back+1);
                visited[A[front]] = true;
                front ++;
            }else{
                visited[A[back]] = false;
                back++;
            }
        }
    return slices;
    }
}
