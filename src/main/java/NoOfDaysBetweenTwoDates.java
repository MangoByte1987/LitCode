public class NoOfDaysBetweenTwoDates {

    private static int noOfLeapYears(int y, int m){
        if(m>=2){
            return (y/4)-(y/100)+(y/400);
        }
        if(m<2){
            return (y-1/4)-(y-1/100)+(y-1/400);
        }
        return 0;
    }
    private static int noOfDaysInMonth(int m){
        int daysInMonth ;
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            daysInMonth = 30;
        } else {
            if (m == 2) {
                daysInMonth =  28;
            } else {
                daysInMonth = 31;
            }
        }
        return daysInMonth;

    }
    public static int daysBetween(int year1, int month1, int day1, int year2, int month2, int day2){
        int totalNoOfDays1 = year1*365 + noOfDaysInMonth(month1) + noOfLeapYears(year1,month1);
        int totalNoOfDays2 = year2*365 + noOfDaysInMonth(month2) + noOfLeapYears(year2,month2);
        return  totalNoOfDays2 - totalNoOfDays1;
    }

    public static void main (String [] args){
        int days = daysBetween(2010,2,1,2014,2,1);
        System.out.println("days : "+days);
    }
}
