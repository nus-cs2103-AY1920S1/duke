package json;

import java.util.*;
import java.util.function.*;

public interface ArrayHandler<T> {
	public void handleElement(Receiver receiver);
	public T handleEnd();

	public static <T> ValueHandler<List<T>> listOf(ValueHandler<T> valueHandler) {
		return new ValueHandler<List<T>>() {
			public ArrayHandler<List<T>> handleArray() {
				return new ListValue<>(valueHandler);
			}
		};
	}

	public class ListValue<T> implements ArrayHandler<List<T>> {
		private final ArrayList<T> list = new ArrayList<>();
		private final ValueHandler<T> valueHandler;
		public ListValue(ValueHandler<T> valueHandler) {
			this.valueHandler = valueHandler;
		}
		public void handleElement(Receiver receiver) {
			T elem = receiver.receive(valueHandler);
			list.add(elem);
		}
		public List<T> handleEnd() {
			return list;
		}
	}
}
