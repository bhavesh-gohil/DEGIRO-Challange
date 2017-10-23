/**
 * 
 */
package com.degiro.trade;

import com.degiro.controller.FileProcesser;
import com.degiro.util.ProcessSchuurs;

/**
 * @author Bhavesh Gohil
 *
 */
public class TradeFinder {

	/**
	 * args[0] pass the file physical location where test cases are loaded
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		FileProcesser loader = new FileProcesser(args[0]);
		ProcessSchuurs.getMaxProfite(loader.load());
	}

}
