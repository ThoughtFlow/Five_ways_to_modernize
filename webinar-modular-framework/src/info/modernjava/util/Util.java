package info.modernjava.util;

public interface Util {

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
	
	static void log(String toLog) {
		System.out.println(toLog);
	}
}
