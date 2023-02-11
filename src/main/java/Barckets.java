import java.util.Stack;

public class Barckets {
    public static void main (String [] args){
        boolean isMatch = checkIfBracketsMatch("([{}])");
        System.out.println(isMatch);
    }

    private static boolean checkIfBracketsMatch(String s){
        Stack<Character> charStack = new Stack<>();
        for(int i=0; i<s.length();i++){
            char bracket = s.charAt(i);
            if(bracket == '(')
                charStack.push(')');
            else if (bracket =='[')
                charStack.push(']');
            else if(bracket == '{')
                charStack.push('}');
            else if(bracket == ')' || bracket == ']' || bracket == '}'){
                if(charStack.isEmpty()) {
                    return false;
                } else {
                    char temp = charStack.pop();
                    if (bracket != temp)
                        return false;
                }
            }
        }
        if(!charStack.isEmpty())
            return false;

        return true;
    }
}

