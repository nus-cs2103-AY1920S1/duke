package org.duke.json;

import java.util.*;
import java.util.function.*;

public interface ArrayHandler<T> {
	public void handleElement(Receiver receiver);
	public T handleEnd();

	public static <T> ValueHandler<ArrayList<T>> listOf(ValueHandler<T> valueHandler) {
		return new ValueHandler<ArrayList<T>>() {
			public ArrayHandler<ArrayList<T>> handleArray() {
				return new ListValue<>(valueHandler);
			}
		};
	}

	public class ListValue<T> implements ArrayHandler<ArrayList<T>> {
		private final ArrayList<T> list = new ArrayList<>();
		private final ValueHandler<T> valueHandler;
		public ListValue(ValueHandler<T> valueHandler) {
			this.valueHandler = valueHandler;
		}
		public void handleElement(Receiver receiver) {
			T elem = receiver.receive(valueHandler);
			list.add(elem);
		}
		public ArrayList<T> handleEnd() {
			return list;
		}
	}
}
