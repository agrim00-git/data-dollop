//@author Agrim Binjola 1154479
import java.io.Serializable;
public class NimPlayer implements Serializable
{
    private String userName;
    private String givenName;//private to prevent outside access to class variable name
    private String familyName;
    private int numberGames;
    private int gamesWon;

    NimPlayer()
    {
        userName=null;
        givenName=null;
        familyName=null;
        numberGames=0;
        gamesWon=0;
    }

    NimPlayer(String userName,String familyName,String givenName)
    {
        this.userName=userName;
        this.givenName=givenName;
        this.familyName=familyName;
        numberGames=0;
        gamesWon=0;
    }

    public void setUserName(String userName)
    {
        this.userName= userName;
    }

    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    public void setFamilyName(String familyName)
    {
        this.familyName=familyName;
    }

    public void incrementGamesPlayed()
    {
        numberGames++;
    }

    public void incrementGamesWon()
    {
        gamesWon++;
    }

    public String getUserName()
    {
        String userName=this.userName;
        return userName;
    }

    public String getGivenName()
    {
        String givenName=this.givenName;
        return givenName;
    }

    public String getFamilyName()
    {
        String familName=this.familyName;
        return familyName;
    }

    public int getNumberGames()
    {
        int numberGames=this.numberGames;
        return numberGames;
    }

    public int getGamesWon()
    {
        int gamesWon=this.gamesWon;
        return gamesWon;
    }

    public void setNumberGames(int numberGames)
    {
        this.numberGames=numberGames;
    }

    public void setGamesWon(int gamesWon)
    {
        this.gamesWon=gamesWon;
    }

    public double getStats()
    {
        double stats=0.0;
        double gamesWon=(double)this.gamesWon;
        double numberGame=(double)this.numberGames;
        if(this.gamesWon!=0) stats=gamesWon/numberGames*100.0;
        return stats;
    }

    public void resetStats()
    {
        numberGames=0;
        gamesWon=0;
    }
    public int removeStone(int i,int j,int initialStones,int count,int turn)
    {
        return(-1);
    }


}
