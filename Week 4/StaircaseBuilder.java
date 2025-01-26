public class StaircaseBuilder {

    public static void main(String[] args) { 
        
        //Variables
        int d = Integer.parseInt(args[0]);
        int totalBricks = Integer.parseInt(args[1]);

        //Declared and initialized variables
        char brick = 'X';
        char empty = ' ';

        //Initializing 2d array and populating with empty spaces of a d x d square
        char[][] stairCase = new char[d][d];

        for(int y = 0; y < d; y++){
            for(int x = 0; x < d; x++){
                stairCase[x][y] = empty;
            }
        }

        //Filling up array from bottom up before shifting to the next column
        for(int y = 0; y < d; y++){
            for(int x = d-1; x >= 0; x--){

                if(totalBricks > 0 && (x+1 > d-1-y)){
                    stairCase[x][y] = brick;
                    totalBricks = totalBricks - 1;
                }
                
            }
        }

        //Printing
        for(int y = 0; y < d; y++){
            for(int x = 0; x < d; x++){
                System.out.print(stairCase[y][x]);
            }
            if(y<d-1)
            System.out.println();
        }

        //Prints out remaining bricks
        System.out.println("\nBricks remaining: " + totalBricks);
    }
}
