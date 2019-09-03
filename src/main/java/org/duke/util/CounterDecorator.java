package org.duke.util;

import java.util.function.Function;

/**
 * Helper class to annotate sequential strings with a number prefix.
 */
public class CounterDecorator implements Function<String, String> {
	private int counter;
	public CounterDecorator(int start) {
		this.counter = start;
	}
	public String apply(String str) {
		String ret = String.format("%d: %s", this.counter, str);
		this.counter++;
		return ret;
	}
}
