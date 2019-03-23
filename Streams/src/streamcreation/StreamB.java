package streamcreation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 
 * 
 * @author athirai
 * Stream operations
 */
public class StreamB {
	
	List<String> stringList = Arrays.asList("Hi", "bye", "see you");
	
	Stream<String> strStream = stringList.stream();
	Stream<String> parallelStream = stringList.parallelStream();
	
	
	
	
	
	/**
	 * Map the items in collection to other objects according to the passed predicate
	 * Intermediate operation. Output of a map operation is a stream which can be used again
	 * 
	 */
	public void mapOperation() {
		
		Stream<String> str = stringList.stream().map(s->s.toLowerCase());
		
		List<String> strglist = str.collect(Collectors.toList()); //converts the stream to be a usable collection again, displays the results of intermediate operation
		
		List<String> strglistPipeline =  stringList.stream().map(s->s.toLowerCase()).collect(Collectors.toList());
		
		System.out.println(strglistPipeline );
		
		
	}
	
	
	/**
	 * 
	 * Produces a new stream that contains elements of an original stream that passes a given test
	 * Intermediate operation. Output of a map operation is a stream which can be used again
	 */
	public void filterOPeration() {
		
		long count = stringList.stream().map(s->s.toLowerCase()).filter(s->s.startsWith("s")).count();
		
		System.out.println(count);
		
	}
	
	
	/**
	 * 
	 * sorts the stream
	 * Intermediate operation. Output of a map operation is a stream which can be used again
	 */
	public void sortOPeration() {
		
		List<String> str =  stringList.stream().map(s->s.toLowerCase()).sorted().collect(Collectors.toList());
		
		
		System.out.println(str);
		
	}
	
	public void distinctOperation() {
		List<String> str =  stringList.stream().distinct().collect(Collectors.toList());
		System.out.println(str);
	}
	
	
	/**
	 * ForEach loops over the objects of the stream applying the supplied function
	 * Terminal operation. Cannot use stream after this
	 */
	public void forEachOPeration() {
		
		stringList.stream().forEach(s -> System.out.println(s.toLowerCase()));

	}
	
	
	/**
	 * Collect operation
	 * Terminal operation. Cannot use stream after this
	 */
	public void collectOPeration() {
		
		List<String> strglistPipeline =  stringList.stream().map(s->s.toLowerCase()).collect(Collectors.toList());

	}
	
	
	/**
	 * reduce operation reduces the elements of a stream
	 * Terminal operation. Cannot use stream after this
	 */
	public void reduceOPeration() {
		
		Optional<String> maxLengthString =  stringList.stream().reduce((string1, string2) -> string1.length() > string2.length() ? string1 : string2);

		maxLengthString.ifPresent(System.out::println);
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		StreamB s = new StreamB();
		s.mapOperation();
		s.filterOPeration();
		s.forEachOPeration();
		s.sortOPeration();
		s.reduceOPeration();
		s.distinctOperation();
	}

}
