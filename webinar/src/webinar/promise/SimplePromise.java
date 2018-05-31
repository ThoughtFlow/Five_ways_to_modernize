package webinar.promise;

import java.util.concurrent.CompletableFuture;
import static webinar.util.Util.log;
import static webinar.util.Util.uncheckedGet;

/**
 * Simple example of a promise wired to take the number 2, double it then square it. 
 */
public class SimplePromise {
	
	public static void main(String[] args) {
		
		 //  2 * 2 ^ 2
		CompletableFuture<Integer> promise = CompletableFuture.supplyAsync(() -> 2).thenApply(r -> r * 2).thenApply(r -> r * r);
		log("Result: " + uncheckedGet(promise));
		
		// Hello + There
		CompletableFuture<String> promise1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> promise2 = CompletableFuture.supplyAsync(() -> "There");
		CompletableFuture<String> combinedPromise = promise1.thenCombine(promise2, (p1, p2) -> p1 + p2);
		log("Result: " + uncheckedGet(combinedPromise));
	}
}
