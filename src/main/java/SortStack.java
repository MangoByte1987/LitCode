import java.util.Stack;
public class SortStack {
    public static void main(String [] args){
        Stack<Integer> s = new Stack<>();
        s.push(3);
        s.push(1);
        s.push(2);

        System.out.println("Original stack" +s);
        sortStack(s);
        System.out.println("After sort" +s);
    }

    private static void sortStack(Stack<Integer> s){
        Stack<Integer> tempStack = new Stack<>();

        while(!s.isEmpty()){
            int num = s.pop();

            while(!tempStack.isEmpty() && tempStack.peek() > num){
                s.push(tempStack.pop());
            }
            tempStack.push(num);
        }
        while (!tempStack.isEmpty()){
            s.push(tempStack.pop());
        }
    }
}
