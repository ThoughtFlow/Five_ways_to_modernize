package webinar.stream;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;
import static webinar.util.Util.log;

/**
 * Here are some ways to implement a prime number counter with serial and parallel streams.
 */
public class PrimeStream {

	private static boolean isSerialPrime(long candidate) {
		return LongStream.rangeClosed(2, (long) Math.sqrt(candidate)).noneMatch(i -> candidate % i == 0);
	}

	private static boolean isParallelPrime(long candidate) {
		return LongStream.rangeClosed(2, (long) Math.sqrt(candidate)).parallel().noneMatch(i -> candidate % i == 0);
	}
	
	private static long serialCount(long range, LongPredicate predicate) {
		return LongStream.rangeClosed(2, range).filter(predicate).count();
	}
	
	private static long parallelCount(long range, LongPredicate predicate) {
		return LongStream.rangeClosed(2, range).parallel().filter(predicate).count();
	}
	
	public static void main(String[] args) {
		log("Prime count for serialCount and isSerialPrime: " + serialCount(1000000, PrimeStream::isSerialPrime));
		log("Prime count for serialCount and isParallelPrime: " + serialCount(1000000, PrimeStream::isParallelPrime));
		log("Prime count for parallelCount and isSerialPrime: " + parallelCount(1000000, PrimeStream::isSerialPrime));
		log("Prime count for parallelCount and isParallelPrime: " + parallelCount(1000000, PrimeStream::isParallelPrime));
	}
}