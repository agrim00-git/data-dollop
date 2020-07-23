/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

import java.util.Scanner;

public class NimAIPlayer extends NimPlayer implements Testable
{
	boolean winningCondition;
	public NimAIPlayer()
	{
		super();
		winningCondition=false;
	}
	public NimAIPlayer(String userName,String familyName,String givenName)
	{
		super(userName,familyName,givenName);
		winningCondition=false;
	}
	
	public String advancedMove(boolean[] available, String lastMove)
	{
		String move = "";
		
		return move;
	}
	private boolean winProduct(int maxRemoval,int Stones)
	{
		int i=0;
		int product=0;
		boolean flag=false;
		while(product<Stones)
		{
			product=i*(maxRemoval+1)+1;
			i++;
		}
		if(product==Stones&&product!=0)flag=true;
		return flag;
	}

	public int removeStone(int maxRemoval, int numberStones,int initialStones,int count,int turn)
	{
		int max=1;
		if(count==0&&turn==2)
		{
			if(winProduct(maxRemoval,numberStones))
				winningCondition=true;
		}
		if(count==0&&turn==1)
		{
			if(!winProduct(maxRemoval,numberStones))
				winningCondition=true;
		}
		System.out.println(getGivenName()+"'s turn - remove how many?\n");
		if(winningCondition)
		{
			for(int i=1;i<=maxRemoval;i++)
			{
				if(winProduct(maxRemoval,numberStones-i))
					max=i;
			}
			if(winProduct(maxRemoval,numberStones-max))
				return max;
		}
		return((int)(Math.round(1+Math.random()*(maxRemoval-1))));
	}
}
