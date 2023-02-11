// Java code to implement the approach
import java.util.*;

class StreamMedian {

    // Function to find the median of stream of data
    public static void streamMed(int A[], int N)
    {
        // Declaring two min heap
        PriorityQueue<Double> g = new PriorityQueue<>();
        PriorityQueue<Double> s = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            // Negation for treating it as max heap
            s.add(-1.0 * A[i]);
            g.add(-1.0 * s.poll());

            if (g.size() > s.size())
                s.add(-1.0 * g.poll());

            if (g.size() != s.size())
                System.out.println(-1.0 * s.peek());
            else
                System.out.println((g.peek() - s.peek())
                        / 2);
        }
    }

    private static void findMedianusingHeap(int arr[]){
        int n = arr.length;
        PriorityQueue < Integer > lowerHalf = new PriorityQueue < > (new Comparator < Integer > () {
            @Override
            public int compare(Integer first, Integer second) {
                return (second - first);
            }
        });

        PriorityQueue < Integer > higherHalf = new PriorityQueue < > ();

        int median;
        for (int size = 1; size <= n; size++) {
            if (!lowerHalf.isEmpty() && lowerHalf.peek() > arr[size - 1]) {
                lowerHalf.add(arr[size - 1]);

                if (lowerHalf.size() > higherHalf.size() + 1) {
                    higherHalf.add(lowerHalf.poll());
                }
            } else {
                higherHalf.add(arr[size - 1]);

                if (higherHalf.size() > lowerHalf.size() + 1) {
                    lowerHalf.add(higherHalf.poll());
                }
            }
            if (size % 2 == 1) {
                if (higherHalf.size() > lowerHalf.size()) {
                    median = higherHalf.peek();
                } else {
                    median = lowerHalf.peek();
                }
            } else {
                median = (lowerHalf.peek() + higherHalf.peek()) / 2;
            }
            System.out.print(median + " ");
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        int A[] = { 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 };
        int N = A.length;

        // Function call
        //streamMed(A, N);
        findMedianusingHeap(A);
    }
}
