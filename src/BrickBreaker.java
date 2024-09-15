import java.util.*;

public class BrickBreaker {
    private static String wall="w";
    private static String ground="g";
    private static String ball="o";
    private static String brick="1";
    public static int ballLife=15;
    public static int[] ballPos=null;
    private static String[][] board=null;
    private static Map<Integer,Integer> brickLife=null;

    public BrickBreaker(int size1,int size2)
    {
        brickLife=new HashMap<>();
        board=new String[size1][size2];
        prepareBoard();
    }

    private void prepareBoard() {
        for(int i=0;i< board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(j==0 || i==0 || j==board[0].length-1) {board[i][j]=wall;}
                else if(i== board.length-1) {board[i][j]=ground;}
                else {board[i][j]=" ";}
            }
        }
        board[board.length-1][(board[0].length)/2]=ball;
        ballPos=new int[]{board.length-1,(board[0].length)/2};

    }
    public void placeBrick(int row,int col,int life)
    {
        board[row][col]=brick;
        int exactPos=getExactPos(row,col);
        brickLife.put(exactPos,life);

    }

    private int getExactPos(int row, int col) {
        return (row*board[0].length)+col+1;
    }
    public void printBoard()
    {
        for(int row=0;row<board.length;row++)
        {
            for(int col=0;col<board[0].length;col++)
            {
                System.out.print(board[row][col]+"  ");
            }
            System.out.println();
            System.out.println();
        }
    }
    public void initiatBall(int ballRow,int ballCol,int rowDirection,int colDirection)
    {

        moveBall(ballRow,ballCol,rowDirection,colDirection);
        if(!board[ballRow][ballCol].equals(ball)) board[ballRow][ballCol]=ground;

    }

    private void moveBall(int ballPosRow, int ballPosCol, int rowDirection, int colDirection) {

        while(!board[ballPosRow][ballPosCol].equals(wall))
        {
            if(board[ballPosRow][ballPosCol].equals(brick))
            {
                moveBallDown(ballPosRow,ballPosCol);
                return;
            }
            movingIllution(ballPosRow,ballPosCol);
            ballPosRow+=rowDirection;
            ballPosCol+=colDirection;
            //----
        }
        attemptWall(ballPosRow,ballPosCol);
        rowDirection=0;
        colDirection*=-1;
        if(colDirection==0) moveBallDown(ballPosRow+1,ballPosCol);
        else moveBall(ballPosRow,ballPosCol+colDirection,rowDirection,colDirection);



    }

    private int reduceBrickLife(int ballPosRow, int ballPosCol) {
        int brickExactPos=getExactPos(ballPosRow,ballPosCol);
        if(ballLife>0)
        {
            brickLife.put(brickExactPos,brickLife.get(brickExactPos)-1);
        }
        ballLife--;
        return brickExactPos;

    }

    private void moveBallDown(int ballPosRow, int ballPosCol) {
        while(ballPosRow!=board.length)
        {
            movingIllution(ballPosRow,ballPosCol);
            ballPosRow++;
        }
        ballPos=new int[]{ballPosRow-1,ballPosCol};
        board[ballPosRow-1][ballPosCol]=ball;

    }

    private void attemptWall(int ballPosRow, int ballPosCol) {
        board[ballPosRow][ballPosCol]=ball;
        printBoard();
        sleepForSec();
        System.out.println();
        board[ballPosRow][ballPosCol]=wall;

    }

    private void movingIllution(int row,int col)  {
       if(board[row][col].equals(brick))
       {
           int brickExactPos=reduceBrickLife(row,col);
           if(brickLife.get(brickExactPos)==0)
           {
               board[row][col]=" ";
           }
           //---------
       }
       else {
           board[row][col] = ball;
           printBoard();
           board[row][col] = " ";
           sleepForSec();
       }
    }

    private void sleepForSec() {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interupted");
        }
    }


    public boolean brickNotFound() {
        boolean NotContain=true;
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j].equals(brick))
                    return false;
            }
        }
        return true;
    }
}
