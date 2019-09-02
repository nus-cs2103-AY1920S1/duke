package org.duke.json;

@FunctionalInterface
public interface Receiver {
	<T> T receive(ValueHandler<? extends T> handler);
}
