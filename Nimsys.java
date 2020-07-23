//@author Agrim Binjola 1154479
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;


public class Nimsys
{
    private static Nimsys obj=new Nimsys();
    private NimPlayer[] player = new NimPlayer[100];
    private int playersIntialized;// keeps track of number of objects initialized
    private static Scanner keyboard=new Scanner(System.in);
    private int numberPlayers;// keeps track of number of current players in the game

    Nimsys()
    {
        playersIntialized=0;
        numberPlayers=0;
        for(int i=0;i<100;i++)
        {
            player[i]=null;
        }
    }
    public Scanner getScanner()
    {
        return keyboard;
    }
    private int getPlayerN0(String userName)// returns the index of the player with the given username
    {
        int i=0;
        for ( i=0;i<numberPlayers;i++)
        {
            if(player[i].getUserName().equals(userName))
                break;
        }
        return i;
    }

    private void copy(NimPlayer player1, NimPlayer player2)//to copy players data without changing the object references
    {
        player1.setUserName(player2.getUserName());
        player1.setGivenName(player2.getGivenName());
        player1.setFamilyName(player2.getFamilyName());
        player1.setNumberGames(player2.getNumberGames());
        player1.setGamesWon(player2.getGamesWon());
    }

    private boolean playerExists(String userName)//used for checking if the player of the given username exists
    {
        boolean flag = false;
        {
            for (int i=0;i<numberPlayers;i++)
            {
                if((player[i]!=null) && (userName.equals(player[i].getUserName())))
                    flag=true;
            }
        }
        return flag;
    }

    private void sort(String order) // used to sort array in descending or ascending order based on the player statistics
    {
        NimPlayer temp = new NimPlayer();
        int s = 0;
        for (int i = 0; i < numberPlayers - 1; i++)
        {
            s=i;
            for (int j = i+1; j < numberPlayers; j++) {
                if (order.equals("asc"))
                {
                    if (player[s].getStats() > player[j].getStats())
                        s=j;

                }
                if(order.equals("desc"))
                {
                    if (player[s].getStats() < player[j].getStats())
                    {
                        s=j;
                    }
                }
            }
            obj.copy(temp,player[s]);
            obj.copy(player[s],player[i]);
            obj.copy(player[i],temp);
        }
    }

    private void alphabetical(int start,int end)//used to sort players in array from start to end parameter alphabeticaaly
    {
        NimPlayer temp=new NimPlayer();
        int s=0;
        for(int i=start;i<end;i++)
        {
            s=i;
            for(int j=i+1;j<=end;j++)
            {
                if(player[s].getUserName().compareTo(player[j].getUserName())>0)
                    s=j;
            }
            obj.copy(temp,player[s]);
            obj.copy(player[s],player[i]);
            obj.copy(player[i],temp);
        }
    }

    private void addPlayer(String userName,String familyName, String givenName)
    {
        if(obj.playerExists(userName)) System.out.println("The player already exists.");
        else
        {
            player[numberPlayers] = new NimHumanPlayer(userName, familyName, givenName);
            playersIntialized++;
            numberPlayers++;
        }
        System.out.print("\n$");
    }

    private void addPlayer(String userName,String familyName, String givenName,int gamesWon, int numberGames)
    {
        if(obj.playerExists(userName))
        {
            System.out.println("player already exists");
        }
        else
        {
            player[numberPlayers] = new NimHumanPlayer(userName, familyName, givenName);
            player[numberPlayers].setGamesWon(gamesWon);
            player[numberPlayers].setNumberGames(numberGames);
            playersIntialized++;
            numberPlayers++;
        }

    }
    private void addAIPlayer(String userName,String familyName, String givenName)
    {
        if(obj.playerExists(userName)) System.out.println("The player already exists.");
        else
        {
            player[numberPlayers] = new NimAIPlayer(userName, familyName, givenName);
            playersIntialized++;
            numberPlayers++;
        }

        System.out.print("\n$");
    }
    private void removePlayer(String userName)//to remove a specific player
    {
        if(!obj.playerExists(userName))
            System.out.println("The player does not exist.");
        else
        {
            int pNumber=obj.getPlayerN0(userName);
            for(int i=pNumber;i<numberPlayers-1;i++)
                obj.copy(player[i],player[i+1]);// shifting the array to remove the players
            numberPlayers--;

        }
        System.out.print("\n$");
    }

    private void removePlayer()//to remove all players
    {
        System.out.println("Are you sure you want to remove all players? (y/n)");
        char ans=keyboard.nextLine().charAt(0);
        if (ans=='y')
        {
            numberPlayers=0;
        }
        System.out.print("\n$");
    }

    private void editPlayer(String userName,String familyName,String givenName)
    {
        if(!obj.playerExists(userName))
            System.out.println("The player does not exist.");
        else
        {
            int pNumber=obj.getPlayerN0(userName);
            player[pNumber].setGivenName(givenName);
            player[pNumber].setFamilyName(familyName);
        }
        System.out.print("\n$");
    }

    private void resetPlayer(String userName)//to reset specific player
    {
        if(!obj.playerExists(userName))
            System.out.println("The player does not exist.");
        else player[getPlayerN0(userName)].resetStats();
        System.out.print("\n$");
    }

    private void resetPlayer()//to reset all players
    {
        System.out.println("Are you sure you want to reset all player statistics? (y/n)");
        char ch=keyboard.nextLine().charAt(0);
        if(ch=='y')
        {
            for(int i=0;i<numberPlayers;i++)
                player[i].resetStats();
        }
        System.out.print("\n$");
    }

    private void displayPlayer(String userName)//display specific player
    {
        if(obj.playerExists(userName)==false)
            System.out.println("The player does not exist.");
        else
        {
            int playerNo=obj.getPlayerN0(userName);
            System.out.println(userName+","+player[playerNo].getGivenName()+","+player[playerNo].getFamilyName()+","+player[playerNo].getNumberGames()+" games,"+player[playerNo].getGamesWon()+" wins");
        }
        System.out.print("\n$");
    }

    private void displayPlayer()//display all players
    {
        obj.alphabetical(0,numberPlayers-1);
        for(int i=0;i<numberPlayers;i++)
            System.out.println(player[i].getUserName()+","+player[i].getGivenName()+","+player[i].getFamilyName()+","+player[i].getNumberGames()+" games,"+player[i].getGamesWon()+" wins");
        System.out.print("\n$");
    }

    private void ranking(String order)
    {
        obj.sort(order);
        int noTies=0;
        for(int i=0;i<numberPlayers-1;i++)
        {
            noTies=0;
            for(int j=i+1;j<numberPlayers;j++)
            {
                if(player[i].getStats()==player[j].getStats())noTies++;
            }
            obj.alphabetical(i,i+noTies);//to resolve ties alphabetically
        }
        for(int i=0;i<numberPlayers&&i<10;i++)
        {
            String g;
            String s1=Math.round(player[i].getStats())+"%";
            String name=" "+player[i].getGivenName()+" "+player[i].getFamilyName();
            int gamePlayed=player[i].getNumberGames();
            if(gamePlayed<10)g=" 0"+gamePlayed+" games ";
            else g="gamePlayed"+" games ";
            System.out.printf("%-5s|%10s|%s",s1,g,name);
            System.out.println();
        }
        System.out.print("\n$");
    }


    private void startGame(int numberStones,int maxRemoval,String userName1,String userName2)throws InputMismatchException, NumberFormatException
    {
        boolean flag1=false,flag2=false;
        flag1=obj.playerExists(userName1);
        flag2=obj.playerExists(userName2);
        if(flag1==false||flag2==false)
            System.out.println("One of the players does not exist.");
        else
        {
            int no1=obj.getPlayerN0(userName1);
            int no2=obj.getPlayerN0(userName2);
            NimGame obj1=new NimGame();
            obj1.game(numberStones,maxRemoval,player[no1],player[no2],keyboard);

        }
        System.out.print("\n$");
    }
    private void exit()throws FileNotFoundException,IOException
    {
        String fileName="players.dat";
        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(fileName));
        for(int i=0;i<numberPlayers;i++)
        {
            if (player[i].getClass() == NimHumanPlayer.class)
                outputStream.writeObject(player[i]);
        }
        outputStream.close();
        System.out.print("\n");
        System.exit(0);
    }
    private void load()
    {
        String fileName="players.dat";
        ObjectInputStream inputStream=null;
        try
        {
            int i=0;
            inputStream=new ObjectInputStream(new FileInputStream(fileName));
            while(true)
            {
                NimPlayer tempplayer;
                tempplayer=(NimHumanPlayer)inputStream.readObject();
                obj.addPlayer(tempplayer.getUserName(),tempplayer.getFamilyName(),tempplayer.getGivenName(),tempplayer.getGamesWon(),tempplayer.getNumberGames());
                i++;
            }
        }
        catch(EOFException eofe)
        {
        }
        catch(FileNotFoundException fnfe)
        {
        }
        catch(IOException | ClassNotFoundException e)
        {
        }
        catch(NullPointerException npe)
        {
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch(IOException| NullPointerException e)
            {
            }
        }

    }

    public static void main(String [] args)
    {
        System.out.println("Welcome to Nim");
        System.out.print("\n$");
        String[] words=new String[10];
        obj.load();
        while(true)
        {
            try
            {
                String s = keyboard.nextLine();
                StringTokenizer str = new StringTokenizer(s, " ,");
                int tokenNo = str.countTokens();
                for (int i = 0; i < tokenNo; i++)
                    words[i] = str.nextToken();
                if (words[0].equals("addplayer"))
                {
                    try
                    {
                        if(tokenNo!=4)throw new InvalidArgumentNumberException();
                        obj.addPlayer(words[1], words[2], words[3]);
                    }
                    catch(InvalidArgumentNumberException iae)
                    {
                        System.out.println(iae.getMessage());
                        System.out.print("\n$");
                    }
                }
                else if (words[0].equals("addaiplayer"))
                {
                    try
                    {
                        if(tokenNo!=4)throw new InvalidArgumentNumberException();
                        obj.addAIPlayer(words[1], words[2], words[3]);
                    }
                    catch(InvalidArgumentNumberException iae)
                    {
                        System.out.println(iae.getMessage());
                        System.out.print("\n$");
                    }
                }
                else if (words[0].equals("rankings"))
                {
                    try
                    {
                        if(tokenNo!=1&&tokenNo!=2)throw new InvalidArgumentNumberException();
                        if (tokenNo == 1 || (words[1].equals("desc") && tokenNo == 2))
                            obj.ranking("desc");
                        else if (words[1].equals("asc") && tokenNo == 2)
                            obj.ranking(words[1]);
                    }
                    catch(InvalidArgumentNumberException iane)
                    {
                        System.out.println(iane.getMessage());
                        System.out.print("\n$");
                    }
                }
                else if (words[0].equals("displayplayer"))
                {
                    try
                    {
                        if (tokenNo == 1) obj.displayPlayer();
                        else if (tokenNo == 2) obj.displayPlayer(words[1]);
                        else throw new InvalidArgumentNumberException();
                    }
                    catch(InvalidArgumentNumberException iae)
                    {
                        System.out.println(iae.getMessage());
                        System.out.print("\n$");
                    }
                }
                 else if (words[0].equals("editplayer"))
                 {
                     try
                     {
                         if(tokenNo!=4)throw new InvalidArgumentNumberException();
                         obj.editPlayer(words[1], words[2], words[3]);
                     }
                     catch(InvalidArgumentNumberException iae)
                     {
                         System.out.println(iae.getMessage());
                         System.out.print("\n$");
                     }
                 }
                else if (words[0].equals("removeplayer"))
                {
                    try
                    {
                        if(tokenNo!=1&&tokenNo!=2)throw new InvalidArgumentNumberException();
                        if (tokenNo == 1) obj.removePlayer();
                        else if (tokenNo == 2) obj.removePlayer(words[1]);
                    }
                    catch(InvalidArgumentNumberException iane)
                    {
                        System.out.println(iane.getMessage());
                        System.out.print("\n$");
                    }
                }
                else if (words[0].equals("resetstats"))
                {
                    try
                    {
                        if(tokenNo!=1||tokenNo!=2)throw new InvalidArgumentNumberException();
                        if (tokenNo == 1) obj.resetPlayer();
                        else if (tokenNo == 2) obj.resetPlayer(words[1]);
                    }
                    catch(InvalidArgumentNumberException iae)
                    {
                        System.out.println(iae.getMessage());
                        System.out.print("\n$");
                    }
                }
                else if (words[0].equals("startgame"))
                {
                    try
                    {
                        if(tokenNo!=5)throw new InvalidArgumentNumberException();
                        obj.startGame(Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[3], words[4]);
                    }
                    catch(InvalidArgumentNumberException iae)
                    {
                        System.out.println(iae.getMessage());
                        System.out.print("\n$");
                    }
                    catch(InputMismatchException ime)
                    {
                        System.out.println("Invalid arguments provided");
                    }
                    catch(NumberFormatException nfe)
                    {
                        System.out.println("Invalid arguments provided");
                    }
                }
                else if (words[0].equals("exit"))
                {
                    try
                    {
                        if(tokenNo!=1)throw new InvalidArgumentNumberException();
                        obj.exit();
                    }
                    catch(InvalidArgumentNumberException iae)
                    {
                        System.out.println(iae.getMessage());
                        System.out.print("\n$");
                    }
                    catch(FileNotFoundException fnfe)
                    {
                        System.out.println("The file players.dat could not be found or opened");
                    }
                    catch(IOException ioe)
                    {
                        System.out.println("The file players.dat could not be written");
                    }
                }
                else throw new InvalidInputException("\'"+words[0]+"\'"+" is not a valid command.");
            }
            catch(InvalidInputException iie)
            {
                System.out.println(iie.getMessage());
                System.out.print("\n$");
            }
        }
    }



}
