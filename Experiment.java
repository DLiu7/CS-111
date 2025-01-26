public class Experiment {
    
    public static void main(String[] args) { 
        
        int random = (int)(Math.random()*1000+1);

        int n = 0;
        
        while(n!=random){
        StdOut.print("Guess a number: ");

        n = StdIn.readInt();

        if(n>random){
            StdOut.println("Too High");
        }else if(n<random){
            StdOut.println("Too Low");
        }else{
            StdOut.println("Correct");
        }
        }

        StdOut.println("The mystery number is " + random);
        }

}
