package org.duke.json;

import java.util.*;

/**
 * Extracts a value given a JSON value context.
 * @param <T> Type of value to extract
 */
public interface ValueHandler<T> {
	/**
	 * Called when the JSON value is a boolean.
	 *
	 * @param val JSON value
	 * @return Extracted value
	 */
	default T handleBoolean(boolean val) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Called when the JSON value is a number.
	 *
	 * @param val JSON value
	 * @return Extracted value
	 */
	default T handleNumber(double val) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Called when the JSON value is a null.
	 *
	 * @return Extracted value
	 */
	default T handleNull() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Called when the JSON value is a string.
	 *
	 * @param val JSON value
	 * @return Extracted value
	 */
	default T handleString(String val) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Called when the JSON value is a JSON object.
	 * @return Object-context value extractor
	 */
	default ObjectHandler<? extends T> handleObject() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Called when the JSON value is a JSON array.
	 * @return Array-context value extractor.
	 */
	default ArrayHandler<? extends T> handleArray() {
		throw new UnsupportedOperationException();
	}

	public class BooleanValue implements ValueHandler<Boolean> {
		public static final BooleanValue INSTANCE = new BooleanValue();

		public Boolean handleString(boolean val) {
			return val;
		}
	}

	public class StringValue implements ValueHandler<String> {
		public static final StringValue INSTANCE = new StringValue();

		public String handleString(String val) {
			return val;
		}
	}

	public class ObjectValue implements ValueHandler<Object> {
		public static final ObjectValue INSTANCE = new ObjectValue();

		public Object handleBoolean(boolean val) {
			return val;
		}

		public Object handleNumber(double val) {
			return val;
		}

		public Object handleNull() {
			return null;
		}

		public Object handleString(String val) {
			return val;
		}

		public ObjectHandler<Map<String, Object>> handleObject() {
			return new ObjectHandler.DictValue<>(INSTANCE);
		}

		public ArrayHandler<ArrayList<Object>> handleArray() {
			return new ArrayHandler.ListValue<>(INSTANCE);
		}
	}

	public static <T> ValueHandler<ArrayList<T>> listOf(ValueHandler<T> elemHandler) {
		return new ValueHandler<>() {
			public ArrayHandler<ArrayList<T>> handleArray() {
				return new ArrayHandler.ListValue<T>(elemHandler);
			}
		};
	}
}
