package webinar.promise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import webinar.util.Util;
import static webinar.util.Util.log;

/**
 * This example show the power of promises. A variable amount of sites are scraped for all their words. These are then cataloged inside a HashMap. 
 * Finally, stats are computed based on the most frequent word found, the average length of a word and the amount of unique words found.
 * 
 *  SiteScraperPromise ----\                                        /----- mostUsedWordPromise
 *  SiteScraperPromise ------ CombinerPromise --- CatalogerPromise ------- averageWordLengthPromise
 *  SiteScraperPromise ----/                                        \----- totalUniqueWordsPromise
 */
public class SiteScrapingPromise {

	private static void scrapeSites(List<String> toScrapeUrls) {
		
		// Use currying to define the siteScrapingFunction
		Function<String, Supplier<List<String>>> siteScraperFunction = 
			url -> () -> Util.toStream(url).map(s -> s.replaceAll("[^\\p{L}\\p{Nd}]+", "|")).map(s -> s.toLowerCase()).flatMap(s -> 
				{
					List<String> words = new ArrayList<>();
					StringTokenizer token = new StringTokenizer(s, "|");
					while (token.hasMoreTokens()) {
						words.add(token.nextToken());
					}
					return words.stream();
				}).collect(Collectors.toList());
			

		BiFunction<List<String>, List<String>, List<String>> combiner = (l1, l2) -> {l1.addAll(l2); return l1;};
		
		// Wire the combiner promise for the first 3 urls only
		CompletableFuture<List<String>> siteScrapingPromise1 = CompletableFuture.supplyAsync(siteScraperFunction.apply(toScrapeUrls.get(0)));
		CompletableFuture<List<String>> siteScrapingPromise2 = CompletableFuture.supplyAsync(siteScraperFunction.apply(toScrapeUrls.get(1)));
		CompletableFuture<List<String>> siteScrapingPromise3 = CompletableFuture.supplyAsync(siteScraperFunction.apply(toScrapeUrls.get(2)));
		CompletableFuture<List<String>> combinerPromise = siteScrapingPromise1.thenCombine(siteScrapingPromise2, combiner).thenCombine(siteScrapingPromise3, combiner);

		// Wire the combiner promise in a chain (the proper way - but harder to understand!)
//		Function<Throwable, List<String>> empty = t -> new ArrayList<String>();
//		CompletableFuture<List<String>> combinerPromise = 
//				toScrapeUrls.stream().map(url -> CompletableFuture.supplyAsync(siteScraperFunction.apply(url)).exceptionally(empty)).
//				reduce(CompletableFuture.completedFuture(new ArrayList<String>()), (combined, next) -> combined.thenCombine(next,  combiner));
		
		// Wire the cataloger and combiner promises together
		CompletableFuture<Map<String, Integer>> catalogerPromise = combinerPromise.thenApply(list -> 
			{
				Map<String, Integer> map = new HashMap<>();
				list.stream().forEach(s -> map.merge(s, 1, (v1, v2) -> ++v1));
				return map;
			});
		
		// Wire the three different statistic promises with the cataloger promise
		//This promise determines the most used word 
		CompletableFuture<Holder<String, Integer>> mostUsedWordPromise = 
				catalogerPromise.thenApplyAsync(
						map -> map.keySet().
							       stream().
							       map(k -> new Holder<String, Integer>(k, map.get(k))).max((h1, h2) -> h1.getU().compareTo(h2.getU())).
							       orElse(new Holder<String, Integer>("None", 0)));
		// This promise determines the average word length 
		CompletableFuture<Long> averageWordLengthPromise = catalogerPromise.thenApplyAsync(
						map -> Math.round(map.keySet().
										      stream().
										      map(k -> new Holder<String, Integer>(k, map.get(k))).
										      mapToInt(h -> h.getV().length()).
										      average().
										      orElse(0)));
		// This promise determines the the total amount of unique words
		CompletableFuture<Integer> totalUniqueWordsPromise = catalogerPromise.thenApplyAsync(map -> map.size());
		
		// Nothing has executed so far. Now, light the match and let it explode!
		try {
			log("Most used word: " + mostUsedWordPromise.get().getV() + " (Occurences: " + mostUsedWordPromise.get().getU() + ")");
			log("Average word length: " + averageWordLengthPromise.get());
			log("Total Unique words scraped: " + totalUniqueWordsPromise.get());
		} catch (InterruptedException | ExecutionException e) {
			log("Could not compute");
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		scrapeSites(Arrays.asList("https://www.oracle.com/index.html", 
						          "https://dzone.com/",
								  "https://jaxenter.com/"));
	}
}