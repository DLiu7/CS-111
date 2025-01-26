public class BusStop {
    public static void main(String[] args) { 
        
        int[] input = new int[args.length];
        int stop = 0;

        for(int i = 0; i < args.length; i++){
            input[i] = Integer.parseInt(args[i]);
        }

        for(int i = 0; i < input.length; i++){
            
            if(input[i] == input[input.length-1]){
                stop = i+1;
                break;
            }
        }

        if(stop == 0){
            System.out.println("1000");
        }else{
            System.out.println(stop);
        }

   }
}
