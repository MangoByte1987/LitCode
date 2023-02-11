import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String [] args) {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        //List<Integer> listInt = Arrays.asList(1,2,3,4,5,6,7,8,9);

        int numOfThread = Runtime.getRuntime().availableProcessors();
        ExecutorService poolOfThreads = Executors.newFixedThreadPool(numOfThread);
        List<Integer> result = new ArrayList<>();
        input.parallelStream().map(i -> result.add(i)).collect(Collectors.toList());
        for(Integer i : result){
            System.out.print(i + ",");
        }
        System.out.print("\n");
        result.clear();
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < input.size() / 2; i++) {
                    result.add(i);
                }
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (int i = input.size()/2; i < input.size() ; i++) {
                    result.add(i);
                }
            }
        };

        poolOfThreads.execute(task1);
        poolOfThreads.execute(task2);

        poolOfThreads.shutdown();

        while(!poolOfThreads.isTerminated()){

        }
      for(Integer i : result)
             System.out.print(i + ",");
    }


}
