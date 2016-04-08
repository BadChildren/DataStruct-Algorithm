import java.util.Scanner;

public class SnakeMatrix {

	static int[][] a = new int[20][20];
	public static void fun(int n)
	{
		int m=1,i,j;
		for(i=0;i<n/2;i++)
		{
			//从左到右方向
			for(j=0;j<n-i;j++)
			{
				if(a[i][j]==0)
					a[i][j]=m++;
			}
			//从上到下方向
			for(j=i+1;j<n-1-i;j++)
			{
				if(a[j][n-1-i]==0)
					a[j][n-1-i]=m++;
			}
			//从右到左方向
			for(j=n-1-i;j>i;j--)
			{
				if(a[n-1-i][j]==0)
					a[n-1-i][j]=m++;
			}
			//从下到上方向
			for(j=n-1-i;j>i;j--)
			{
				if(a[j][i]==0)
					a[j][i]=m++;
			}
		}
		//如果是奇数的话，最后中间的那个数为m
		if(n%2==1)
			a[n/2][n/2]=m;
	}
	
	public static void main(String[] args) {
			
		Scanner cin = new Scanner(System.in);
		int n,i,j;
		n=cin.nextInt();
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
				a[i][j]=0;
		fun(n);
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
	}

}

/*
例如输入：4
1	2	3	4
12	13	14	5
11	16	15	6
10	9	8	7
*/