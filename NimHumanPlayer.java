//@author Agrim Binjola 1154479
import java.util.Scanner;
import java.io.Serializable;
public class NimHumanPlayer extends NimPlayer implements Serializable
{

    NimHumanPlayer()
    {
        super();
    }

    NimHumanPlayer(String userName,String familyName,String givenName)
    {
        super(userName,familyName,givenName);
    }


    public int removeStone(int maxRemoval,int numberStones,int initialStones,int count,int turn)
    {
        int remove=0;//local variable
        Nimsys nimsysObj=new Nimsys();
        System.out.println(getGivenName()+"'s turn - remove how many?\n");
        remove=nimsysObj.getScanner().nextInt();
        nimsysObj.getScanner().nextLine();
        return(remove);
    }
    public static void main(String[] args){}
}