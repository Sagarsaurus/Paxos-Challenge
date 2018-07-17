import java.util.LinkedList;

public class ReplaceX {
	static LinkedList<String> queue = new LinkedList<String>();
	
	//This algorithm will run in O(2^k) where k is the number of wildcards in the string
	
	//We may have to iterate through the entire string 2^n. We generate two strings per wildcard

	public static void main(String[] args) {
		
		String input = args[0];
		
		queue.push(input);
		while(!queue.isEmpty()) {
			boolean foundX = false;
			boolean unmodified = true;
			String curr = queue.pop();
			String temp = "";
			for(int i = 0; i < curr.length(); i++) {
				if(curr.charAt(i) == 'X' && !foundX) {
					unmodified = false;
					String one = temp + "0" + curr.substring(i+1);
					String two = temp + "1" + curr.substring(i+1);
					queue.push(one);
					queue.push(two);
					//since we're only handling one X at a time, we'll treat this as a new string later
					break;
				} else {
					temp+=curr.charAt(i);
				}
			}
			
			if(unmodified) {
				System.out.println(curr);
			}			
		}

	}

}
