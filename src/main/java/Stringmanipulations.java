import java.util.stream.StreamSupport;

public class Stringmanipulations {
    private static boolean isUniqueCharUsingArr (String str){
        boolean [] chars = new boolean [256];
        for(int i =0 ; i< str.length() -1 ; i ++){
            if(chars[str.charAt(i)])
                return false;
            else{
                chars[str.charAt(i)] = true;
            }
        }
        return true;
    }

    public static String fillSpaces(String str,String filler){
        char [] chars = str.toCharArray();
        char [] finalStr = new char[chars.length-2];
        int index =0;
        for(int i =0;i<str.trim().length();i++){
            if(chars[i] == ' '){
                finalStr[index] = '%';
                finalStr[index+1] = '2';
                finalStr[index+2] = '0';
                index = index +3;
            }else{
                finalStr[index]=chars[i];
                index++;
            }
        }
        return new String(finalStr);
    }

    private static boolean oneEditAway(String s1,String s2){
        if(Math.abs(s1.length() - s2.length()) > 1)
            return false;
        String small = s1.length() < s2.length() ? s1 : s2;
        String big = s1.length() < s2.length() ? s2 : s1;

        int i =0;
        int j=0;
        boolean diff = false;
        while(j<big.length() && i<s1.length()){
            if(small.charAt(i) != big.charAt(j)){
                if(diff) return false;
                diff = true;
                if(big.length() == small.length())
                    i++;
            }else{
                 i++;
            }
            j++;
        }
        return  true;
    }

    private static boolean isPermutationOfPalindrome(String s ){
            int [] chars = new int [26];
            for(int i= 0 ; i<s.length();i++){
                int c = s.charAt(i);
                if(c >= 'a' && c<='z'){
                    chars[c -'a']++;
                }
                if(c>='A'&& c<='Z'){
                    chars[c -'A']++;
                }
            }
            int oddcnt = 0;
            for(int cnt : chars){
                if(cnt%2 ==1)
                    oddcnt++;
            }
            if(oddcnt<=1) {
                return true;
            }
        return false;
    }

    public static  void main(String [] args){
        System.out.println("Srting with unique chars : "+isUniqueCharUsingArr("manngesh"));
        System.out.println(fillSpaces("I am Mangesh      ","%20"));
        System.out.println("isPermutationOfPalindrome : " + isPermutationOfPalindrome("Tact Coa"));
        System.out.println("oneEdit away : "+oneEditAway("pale","spale"));

        String x = "abc";
        String y = "abc";

        x=x.concat(y);
        System.out.print(x);
    }
}
