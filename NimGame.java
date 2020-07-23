//@author Agrim Binjola 1154479
import java.io.IOException;
import java.util.Scanner;
public class NimGame
{
    private static NimGame obj=new NimGame();
    private void printStones(int n)//method to print stones takes the nummber of stones as input and prints appropriate result
    {
        System.out.print(n+" stones left: ");
        for(int i=1;i<=n;i++)
        {
            if(i==n) System.out.print("*");
            else System.out.print("* ");
        }
        System.out.println();

    }

    private boolean winnerCheck(NimPlayer player,int numberStones)//method to check if someone has won the game based on number of stones left and print the appropriate result
    {
        boolean flag= false;
        if (numberStones<=0)
        {
            System.out.println("Game Over\n"+player.getGivenName()+" "+player.getFamilyName()+" wins!");
            player.incrementGamesWon();
            flag=true;
        }
        else obj.printStones(numberStones);
        return flag;
    }
    public void game(int numberStones,int maxRemoval,NimPlayer player1, NimPlayer player2,Scanner keyboard)
    {
        boolean flag=true;
        int stonesRemoved=0;
        int smallerValue=0;
        int count=0;
        player1.incrementGamesPlayed();
        player2.incrementGamesPlayed();
        System.out.println("\nInitial stone count: "+numberStones);
        System.out.println("Maximum stone removal: "+maxRemoval);
        System.out.println("Player 1: "+player1.getGivenName()+" "+player1.getFamilyName());
        System.out.println("Player 2: "+player2.getGivenName()+" "+player2.getFamilyName()+"\n");
        printStones(numberStones);
        while(numberStones>0)
        {
            flag=true;
            while(flag)
            {
                try
                {
                    smallerValue=Math.min(numberStones,maxRemoval);
                    stonesRemoved=player1.removeStone(smallerValue,numberStones,numberStones+stonesRemoved,count,1);
                    if(stonesRemoved<1||stonesRemoved>smallerValue)throw new InvalidMoveException();
                    flag=false;
                }
                catch(InvalidMoveException ime)
                {
                    System.out.println("Invalid move. You must remove between 1 and "+smallerValue+" stones.\n");
                    obj.printStones(numberStones);
                }
            }
            numberStones-=stonesRemoved;
            if(obj.winnerCheck(player2,numberStones)) break;//to check if someone has won and exit the loop when that happens
            flag=true;
            while(flag)
            {
                try
                {
                    smallerValue=Math.min(numberStones,maxRemoval);
                    stonesRemoved=player2.removeStone(smallerValue,numberStones,numberStones+stonesRemoved,count,2);
                    if(stonesRemoved<1||stonesRemoved>smallerValue)throw new InvalidMoveException();
                    flag=false;
                }
                catch(InvalidMoveException ime)
                {
                    System.out.println("Invalid move. You must remove between 1 and "+smallerValue+" stones.\n");
                    obj.printStones(numberStones);
                }
            }
            numberStones-=stonesRemoved;
            count++;
            if (obj.winnerCheck(player1,numberStones)) break;

        }
    }

    public static void main(String[] args) {}
}
