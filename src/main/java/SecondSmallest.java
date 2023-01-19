public class SecondSmallest {
        static void print2smallest(int arr[]){
            if(arr.length < 2){
                System.out.println(" Invalid Input ");
                return;
            }
            int first=Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;
            int arr_size = arr.length;

            for(int i=0;i<arr_size;i++){
                if(arr[i] < first){
                    second=first;
                    first = arr[i];
                }else if (arr[i] < second && arr[i] !=first){
                    second = arr[i];
                }
            }

            if (second == Integer.MAX_VALUE)
                System.out.println("There is no second" +
                        "smallest element");
            else
                System.out.println("The smallest element is " +
                        first + " and second Smallest" +
                        " element is " + second);


        }

        public static void main(String args[]){

            int [] a = {12,1,10,13,8,15};
            print2smallest(a);

        }

    }

