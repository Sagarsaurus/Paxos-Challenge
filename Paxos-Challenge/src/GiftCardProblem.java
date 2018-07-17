import java.io.File;
import org.apache.commons.io.input.ReversedLinesFileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class GiftCardProblem {

	private static Scanner scan;
	private static ReversedLinesFileReader reverseReader;
	private static File inputFile;
	private static int giftCardValue;
	
	public static void main(String[] args) throws IOException {
		if(args.length==2) {
			inputFile = new File(args[0]);
			giftCardValue = Integer.parseInt(args[1]);
		} else {
			System.out.println("Too few arguments!");
			return;
		}
	
		scan = new Scanner(inputFile);
		reverseReader = new ReversedLinesFileReader(inputFile, Charset.defaultCharset());
		
		String startToken = scan.nextLine();
		String endToken = reverseReader.readLine();
		boolean finished = false;
		int currentMax = -1;
		String ret = "Not Possible";
		
		while(!finished) {

			int startValue = Integer.parseInt(startToken.split(",")[1].trim());
			int endValue = Integer.parseInt(endToken.split(",")[1].trim());
			int sum = startValue + endValue;
			
			//handles both even and odd length lists
			if(startValue > endValue || startToken.equals(endToken)) {
				finished = true;
			}
			
			//if we have the desired sum, we're done
			else if(sum == giftCardValue) {
				finished = true;
				ret = startToken + ", " + endToken;
			} 
			
			else if(sum < giftCardValue) {
				
				if(sum > currentMax) {
					ret = startToken + ", " + endToken;
					currentMax = sum;
				}
				
				startToken = scan.nextLine();
				
			} else if(sum > giftCardValue) {
				endToken = reverseReader.readLine();
			}
			
		}
		
		System.out.println(ret);
	}
}
