package webinar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface Util {

	static long startTime = System.currentTimeMillis() / 1000;
	
	static Stream<String> toStream(String stringedUrl) throws RuntimeException {
		try {
	       URL url = new URL(stringedUrl);
		   return new BufferedReader(new InputStreamReader(url.openConnection().getInputStream())).lines();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e) {
			// Swallow the error
		}
		
	}
	
	static void doWork(int processingTime) {
		sleep(processingTime);
	}
	
	static void sleep(double seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		}
		catch (InterruptedException e) {
			// Swallow the error
		}
		
	}
	
	static <T> T uncheckedGet(Future<T> future) {
		T result = null;
		
		try {
			result = future.get();
		} catch (InterruptedException | ExecutionException e) {
		
		}
		
		return result;
	}
	
	static void log(String toLog) {
		long time = System.currentTimeMillis() / 1000 - startTime;
		System.out.println(time + ": " + toLog);
	}
}
