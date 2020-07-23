import java.util.Scanner;
public class BurntArea
{
    public static int n=5;
    public static int[][]visit={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    static int DFSSize(int [][]input, int row, int col)
    {
        if(input[row][col]==0)
            return 0;
        int size=1;
        visit[row][col]=1;
        int[] xMovement={0,0,-1,1};
        int[] yMovement={-1,1,0,0};
        for(int i=0;i<4;i++)
        {

            if(row+xMovement[i]<0||col+yMovement[i]<0||row+xMovement[i]>=n||col+yMovement[i]>=n)
                continue;
            if (visit[row+xMovement[i]][col+yMovement[i]]==0)
                size+=DFSSize(input,row+xMovement[i],col+yMovement[i]);

        }
        return size;
    }

    public static void main(String[] args)
    {
        int[][] input={{1,1,1,1,0},{1,1,0,0,1},{1,0,0,0,1},{1,1,1,1,0},{1,1,1,1,1}};
        for(int i=0;i<5;i++) {
            for (int j = 0; j < n; j++) {
                visit[i][j] = 0;
            }
        }
        int si=0;
        int maxsize=0;

        for( int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(input[i][j]==1&&visit[i][j]==0)
                    si=DFSSize(input,i,j);
                maxsize=Math.max(si,maxsize);
            }
        }
        System.out.println(maxsize);
    }
}
