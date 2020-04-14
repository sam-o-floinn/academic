import java.util.Scanner;

public class BinaryNumberGeneration 
{
	public static void main( String[] args ) 
	{
		try
		{
			// This is the scanner Object created to read in the inputs
			Scanner sc = new Scanner(System.in);
				
			System.out.printf("Please enter the possible binary n-digit number: ");
			
			// This reads in the first input
			int possibleNumbers = sc.nextInt();
			
			// This will return the possible binary combinations
			// Example 2 ^ 8 = 256 and 2 ^ 32 = 4294967296
			int binaryPower = (int) Math.pow(2, possibleNumbers);
			
			for(int i = 0; i < binaryPower ;i++ )
			{
				// This will return the the number in its binary form
				System.out.println(Integer.toBinaryString(i));
			}
		}
		catch(Exception e)
		{
			System.out.println("You did not enter correct parameters");
		}
		finally
		{
			System.exit(0);
		}
	}
}
