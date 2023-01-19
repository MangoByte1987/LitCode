public class PowerOfTen {
    private static boolean powerOfTen(int input){
        if(input == 0 || input % 10 !=0)
                return false;
        while (input >= 10 && input % 10 == 0){
            input /= 10;
        }
    return input == 1;
    }

    public static void main(String args[]){
        int input = 600;
        System.out.println(powerOfTen(input));
    }

}
