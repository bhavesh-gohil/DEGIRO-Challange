/**
 * 
 */
package com.degiro.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.degiro.domain.Schuurs;

/**
 * @author Bhavesh Gohil
 *
 */
public class ProcessSchuurs {

	private static int count = 1;
	private static int total = 0;
	private static int index = 1;

	/**
	 * Calculate the Maximum profit in schuurs and print the detail
	 * 
	 * @param listSchuurs
	 */
	public static void getMaxProfite(List<Schuurs> listSchuurs) {
		// @formatter:off
		listSchuurs.stream()
				   .map(calculateProfiteFromListOfFluts)
				   .forEach(printdetail);
		// @formatter:on
	}

	/**
	 * function to calculate profit in schuurs 
	 */
	private static Function<Schuurs, Map<Integer, List<Integer>>> calculateProfiteFromListOfFluts = schuurs -> {

		Map<Integer, List<Integer>> tempList = new HashMap<>();
		total = 0;
		index = 1;
		// @formatter:off
		 schuurs.getAllFluts().stream()
							  .map(n -> 10 - n)
							  .forEach(integer ->{ //Quick Solution calculate the profit/loss and make total in order to get max benefit
								    	total = total + integer;
										if (!tempList.containsKey(total)) {
											tempList.put(total, new ArrayList<Integer>());
										}
										tempList.get(total).add(index++);
								    });							    
		// @formatter:on
		return tempList;
	};

	/**
	 * function to print the result Detail
	 */
	private static Consumer<Map<Integer, List<Integer>>> printdetail = value -> {
		// @formatter:off
		int maxProfite = value.keySet()
							  .stream()
							  .mapToInt(x -> x)
							  .summaryStatistics()
							  .getMax();
		
		List<Integer> listOfBestFlut = value.get(maxProfite).stream()
							 								.limit(10)
							 								.collect(Collectors.toList());
		// @formatter:on
		System.out.println("schuurs " + count++);
		System.out.println("Maximum profit is " + maxProfite);
		System.out.print("Number of fluts to buy " + listOfBestFlut.toString());
		System.out.println();
	};
}
