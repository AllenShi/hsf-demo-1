package net.yarn.hsf.demo1.api;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author sjl
 *
 */

public interface EchoService {
	
	/**
	 * Simple echo method
	 * @param input
	 * @return
	 */
	public String echo(String input);
	
	
	/**
	 * Emulate a reply with long waiting time
	 * @param input
	 * @param time
	 * @param unit
	 * @return
	 * @throws InterruptedException
	 */
	public String delayedReply(String input, int time, TimeUnit unit) throws InterruptedException;

}
