
import java.util.Scanner;
class binSearch
{
    static int binaryLess(int[] a,int k)
    {
        int lo=0,comb=0,mid=0,high=a.length-1;
        while (lo <= high)
        {
            mid=(lo+high)/2;
            if (a[mid]<=k)
            {
                comb=mid;
                lo=mid+1;
            }
            else high=mid-1;
        }
        System.out.println(comb);
        return comb;
    }
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter budget");
        int k=s.nextInt();
        System.out.println("Enter size");
        int len=s.nextInt();
        int[] a=new int[len];
        System.out.println("enter prices");

        for(int i=0;i<len;i++)
            a[i]=s.nextInt();
        int sum=0,lo=0,high=len-1,mid=0,poin=-1;

        for(int i=0;i<len;i++)
        {
            int[] c=new int[len-i];
            for(int j=0;j<len-i;j++)
                c[j]=a[j+i];
            sum=sum+binaryLess(c,k-a[i]);
        }
        System.out.println(sum);
    }
}
