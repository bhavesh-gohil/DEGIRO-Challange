/**
 * 
 */
package com.degiro.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.degiro.domain.Schuurs;

/**
 * @author Bhavesh Gohil
 *
 */
public class FileProcesser {

	private final String filePath;

	private static int batchCount = 0;
	private static int quentitySchuurs = 0;
	private static Schuurs schuurs;

	public FileProcesser(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Convert flat file to java domain
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Schuurs> load() throws Exception {
		try (Stream<String> streamLine = Files.lines(Paths.get(filePath))) {
			// @formatter:off
			 Map<Integer, List<Schuurs>> collectionofSchuurs= streamLine.filter(isBatchCompleted)					 													
							 									   		.map(line -> schuurs)
							 									   		.collect(Collectors.groupingBy(Schuurs::getId));
			 return collectionofSchuurs.entrySet()
					 				   .stream()
					 				   .map(value -> value.getValue())
					 				   .flatMap(value -> value.stream())
					 				   .collect(Collectors.toList());
			// @formatter:on
		}

	}

	/**
	 * function to process the batch of fluts until it finish
	 */
	private static Predicate<String> isBatchCompleted = streamLine -> {
		if (quentitySchuurs == 0) {
			quentitySchuurs = Integer.parseInt(streamLine.toString());
			schuurs = new Schuurs(++batchCount, new ArrayList<>());
			return false;
		}
		// @formatter:off
		List<Integer> processBatch = Arrays.asList(streamLine.toString()
										   .split(" "))
										   .stream()
										   .mapToInt(n -> Integer.parseInt(n))
										   .boxed()
										   .collect(Collectors.toList());
		// @formatter:on
		quentitySchuurs--;
		processBatch.remove(0); // first element indicate numbers of fluts,
								// remove from list of fluts
		schuurs.getFluts().add(processBatch);
		return quentitySchuurs == 0 ? true : false;
	};

}
