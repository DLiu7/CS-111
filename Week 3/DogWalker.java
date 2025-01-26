public class DogWalker {
    public static void main(String[] args) {
        
        int x = 0;
        int y = 0;
        int n = Integer.parseInt(args[0]);
        System.out.println("(0,0)");

        while(n>0){
        
        if((double) Math.random()*2-1 <= 0.25){
            //West
            x = x-1;
        }else if((double) Math.random()*2-1 <= 0.5){
            //East
            x = x+1;
        }else if((double) Math.random()*2-1 <= 0.75){
            //North
            y = y+1;
        }else if((double) Math.random()*2-1 <= 1){
            //South
            y = y-1;
        }
        
        n = n-1;
        System.out.println("(" + x + "," + y + ")");
        }

        double distance = (Math.pow(x, 2)+ Math.pow(y,2));
        System.out.print("Squared Distance = " + distance);
    }
}
