import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test {

    public static void main(String args[]) {
        System.out.println("hello mac !!");


        int[] num = { 3, 5, 2, 4, 1 };
        int size = num.length;
        // 8,6,7,9,0,2,1,12,89
        int d = 6;

        int appIndex = d % size;
        System.out.println("appIndex: " + appIndex);
        int appId = num[appIndex];

        for (int i = appIndex; i > 0; i--) {
            num[i] = num[i - 1];
        }
        num[0] = appId;

        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(1000 ^ 2000);
        for (int i = 0; i < num.length; i++) {
            System.out.print(" " + num[i]);
        }

       /* List<String> sampleList = Arrays.asList("java", "kotlin");

        String resultString = sampleList.stream()
                .map((var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));

        assertThat(resultString, is("JAVA, KOTLIN"));

        Optional<String> emptyOpt = Optional.of("Mangesh");

        ArrayList<String> students = new ArrayList<>(10);
        students.add("Mangesh");
        students.add("Prajakts");
        students.add("Prayansh");

        //Consumer
        Consumer<String> printItem = s -> System.out.println(s);
        students.forEach(printItem);

        //function
        Function<Integer, Integer> doublly = n -> n*n;
        doublly.apply(8);

        //Predicate
        IntPredicate fivideBy3 = i -> i % 3 == 0;
        fivideBy3.test(3);

        List <Integer> numbers =  List.of(2,4,5,6,7,8);
        List<Integer> doublyList = numbers.stream()
                .map(n -> n*n)
                .filter( s -> s > 20)
                .collect(Collectors.toList());
       // doublyList.stream().forEach(s->System.out.print(s + " "));

            int num = 5;
        String season = switch (num) {
                            case 0 -> "1";
                            case 2 -> "2";
                            default -> { yield "test" ;}

        };
       // System.out.println("\n"+season);


        char [] charArr = new char [4];
        charArr[0] = 'q';
        String s = "racecare";

        for (int i = 0 , j= s.length() -1  ; i<j ; i++,j--){
            if(s.charAt(i) != s.charAt(j)) {
                //System.out.println("fasle");
                break;
            }

        }

        Node<Integer> head = buildNode(8);
        Node<Integer> nodeA = buildNode(3);
        Node<Integer> nodeB = buildNode(18);
        Node<Integer> nodeC = buildNode(12);
      //  Node<Integer> nodeD = buildNode(1);

        head.setNext(nodeA);
        nodeA.setNext(nodeB);
        nodeB.setNext(nodeC);
       // nodeC.setNext(nodeD);

       // printLinkedList(head);
       // deleteMiddle(head);
       // printLinkedList(head);

        //printBinaryNumQueue(10);
        int arr [] = {1,2,3};
        printSubarrays(arr,0,0);

        ConcurrentHashMap <String,Integer> chm = new ConcurrentHashMap<>();
*/
    }

    private static void printBinaryNumQueue(int n ){
        if(n <=0)
            System.out.println("nothing to print");
        Queue <Integer> binaryNums = new LinkedList<Integer>();
        binaryNums.add(1);
        for(int i = 1 ; i<= n ; i++){
            Integer current = binaryNums.remove();
            System.out.print(current + "\n");
            binaryNums.add(current * 10);
            binaryNums.add(current * 10 + 1);
        }
     }
    private static Node<Integer> deleteMiddle(Node<Integer> head) {
        if(head == null || head.getNext() == null)
            return head;

        Node fast = head;
        Node slow = head;
        Node previous = null;

         while(fast != null && fast.getNext() !=null) {
             fast = fast.getNext().getNext();
             previous = slow;
             slow = slow.getNext();
         }
         previous.setNext(slow.getNext());
         return head;
    }

    private static void printLinkedList(Node<Integer> head) {
        while(head != null) {
            System.out.println(head.getData());
            head = head.getNext();
        }
    }

    public static Node <Integer> buildNode(int data){
       Node node = new Node();
        node.setData(data);
        node.setNext(null);
        return node;
    }

    private static void printSubarrays( int arr [],int start, int end){
        if(end == arr.length)
            return;
        else if(start > end ) {
            printSubarrays(arr, 0, end + 1);
        } else {
            System.out.print("[");
                for(int i = start ; i < end ; i++) {
                System.out.print(arr[i] + ", ");
            }
            System.out.println(arr[end] + "]");
            printSubarrays(arr,start +1 , end);
        }
        return;
    }
}
class  Node <D> {
    private D data;
    private Node next;

    public D getData(){return data;}
    public void setData(D data){this.data = data;}
    public Node getNext(){return next;}
    public void setNext(Node next){this.next = next;}
}

