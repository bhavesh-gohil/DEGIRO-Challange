/**
 * 
 */
package com.degiro.domain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bhavesh Gohil
 *
 */
public class Schuurs {

	private final int id;
	private List<List<Integer>> fluts;

	/**
	 * @param id
	 * @param fluts
	 */
	public Schuurs(int id, List<List<Integer>> fluts) {
		super();
		this.id = id;
		this.fluts = fluts;
	}

	/**
	 * @return the fluts
	 */
	public List<List<Integer>> getFluts() {
		return fluts;
	}

	/**
	 * @param fluts
	 *            the fluts to set
	 */
	public void setFluts(List<List<Integer>> fluts) {
		this.fluts = fluts;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public List<Integer> getAllFluts() {
		// @formatter:off
		return fluts.stream()
					.flatMap(value -> value.stream())
					.collect(Collectors.toList());
		// @formatter:on
	}
}
