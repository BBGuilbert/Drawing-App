package streamcreation;

import java.util.Arrays;
import java.util.List;

public class StreamC {
	List<String> stringList = Arrays.asList("Hi", "bye", "see you");
	
	
	public void skipOperation() {
		stringList.stream().skip(1).forEach(System.out::println);
	}
	
	
	public void match() {
		boolean allMatch = stringList.stream().allMatch(str -> str.startsWith("s"));
		
		boolean anyMatch = stringList.stream().anyMatch(str -> str.startsWith("s"));
		
		System.out.println(allMatch + " " + anyMatch);
		
	}
	
	
	
	public static void main(String[] args) {
		
		StreamC stream = new StreamC();
		stream.skipOperation();
	}
	
	
}
