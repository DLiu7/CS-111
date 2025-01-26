public class Buses {
    public static void main(String[] args) {

        int busNumber = Integer.parseInt(args[0]);
        int sum = 0;
        int digit = 0;

        while(busNumber>0){
            digit = busNumber%10;
            sum += digit;
            busNumber = busNumber/10;
        }

         if(busNumber < 0){
                System.out.print("ERROR");
            }else if(sum%2 == 0){
            System.out.print("LX");
            }else{
            System.out.print("H");
            }
        
    }
}
