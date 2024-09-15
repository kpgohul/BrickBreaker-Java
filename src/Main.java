import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BrickBreaker game = new BrickBreaker(8, 7);
        game.placeBrick(3, 4, 2);
        game.placeBrick(3, 2, 2);
        game.placeBrick(4, 2, 2);
        game.placeBrick(4, 4, 2);
        game.placeBrick(3, 3, 2);
        game.placeBrick(4, 3, 2);
        game.printBoard();
//        game.initiatBall(game.ballPos[0], game.ballPos[1], -1, +1);
//        game.initiatBall(game.ballPos[0], game.ballPos[1], -1, 0);
        while(true)
        {
            if(game.ballLife<=0)
            {
                System.out.println("the game is over!!!");
                System.exit(0);
            }
            if(game.brickNotFound())
            {
                System.out.println("The game is over!!!");
                System.exit(0);
            }
            game.printBoard();
            System.out.println("enter your desire direction  ( left / right / straight ): ");
            String opinion=new Scanner(System.in).nextLine();
            switch(opinion)
            {
                case "left" -> {
                    game.initiatBall(game.ballPos[0], game.ballPos[1], -1, -1);
                    break;
                }
                case "right" -> {
                    game.initiatBall(game.ballPos[0], game.ballPos[1], -1, 1);
                    break;}
                case "straight" -> {
                    game.initiatBall(game.ballPos[0], game.ballPos[1], -1, 0);
                    break;
                }
                default ->  {
                    System.out.println("ada mantha puthi crtaah na option aaa kudu!!!");
                }
            }
        }
    }
}