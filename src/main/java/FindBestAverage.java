import java.util.Arrays;
import java.util.*;
public class FindBestAverage {

    public static void main (String args []){
        String [] [] data = new String [] [] {{"bob","-10"}, {"Mike", "-35"},{"Bob", "-52"}, {"Jason","-35"}, {"Mike", "-55"}, {"Mike", "-99"}};
        System.out.println(findBestAveageScore(data));
    }

    private static int findBestAveageScore(String[][] scores) {
        if (scores.length == 0) return -1;
        Map <String,Integer> noOfSubjects = new HashMap();
        Map<String ,Integer> totalMarks = new HashMap();

        for(int i=0; i<scores.length; i++){
            noOfSubjects.put(scores[i][0],noOfSubjects.getOrDefault(scores[i][0],0)+1);
            totalMarks.put(scores[i][0],totalMarks.getOrDefault(scores[i][0],0)+Integer.parseInt(scores[i][1]));
        }
         int max = Integer.MIN_VALUE;
        for(String student :totalMarks.keySet()) {
            System.out.println(totalMarks.get(student));
            System.out.println(noOfSubjects.get(student));
            int avg = totalMarks.get(student)/noOfSubjects.get(student);
            System.out.println("student: "+ student + " avg :"+avg);
            max = Math.max(max,avg);
        }
        return  max;
    }
}
