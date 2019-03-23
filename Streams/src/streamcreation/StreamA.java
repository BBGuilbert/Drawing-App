package streamcreation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 
 * 
 * @author athirai
 *
 *	Stream creation examples
 */
public class StreamA {
	
	public void createEmptyStream() {
		// Generic Stream Stream<T>
		Stream<String> streamEmpty = Stream.empty(); 
	}
	
	
	public void createStreamFromArray() {
		
		String[] str = {"hi","bye"};
		
		Stream<String> strStream = Arrays.stream(str); 
		
		strStream = Stream.of("hi","bye");
		
	}
	
	public void createStreamFromList() {
		List<Integer> numList = new ArrayList<Integer>();
		numList.add(1);
		numList.add(5);
		numList.add(22);
	
		Stream<Integer> integerStream = numList.stream(); 
	}
	
	public void createStreamFromBuilder() {
		
		Stream<String> streambuilder = Stream.<String>builder().add("hi").add("bye").build();
	}
	
	
	
	
	public void createStreamFromGenerate() {
		
		Random rnd = new Random();
		Stream.generate(rnd::nextInt).limit(5).forEach(System.out::println);
	}
	
	public void createStreamFromInterate() {
		Stream.iterate(30, x -> x+2).limit(3).forEach(System.out::println);
	}
	
	
	public static void main(String[] args) {
		
		StreamA s = new StreamA();
		s.createStreamFromList();
		s.createStreamFromArray();
		s.createStreamFromGenerate();
		s.createStreamFromInterate();
	}
}
