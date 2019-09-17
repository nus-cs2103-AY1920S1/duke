package org.duke.json;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This is a structure capable of receiving consecutive JSON fields from a JSON object,
 * and finally producing a T value.
 *
 * @param <T> Value to produce
 */
public interface ObjectHandler<T> {
    static <T> ValueHandler<Map<String, T>> mapOf(ValueHandler<T> valueHandler) {
        return new ValueHandler<>() {
            public ObjectHandler<Map<String, T>> handleObject() {
                return new DictValue<>(valueHandler);
            }
        };
    }

    /**
     * Handles a JSON object field name + value pair.
     *
     * @param name     Field name
     * @param receiver {@link ValueHandler} callback, for field value
     */
    void handleField(String name, Receiver receiver);

    /**
     * Handle the end of the object, and return the final value.
     *
     * @return Completed value
     */
    T handleEnd();

    /**
     * Converts a {@link ObjectHandler} returning {@param <T>} to a {@link ObjectHandler} returning {@param <U>}
     *
     * @param function Mapping function
     * @param <U>      Target output type
     * @return Wrapped {@link ObjectHandler} returning a processed {@param <U>} value.
     */
    default <U> ObjectHandler<U> map(Function<T, U> function) {
        return new ObjectHandler<>() {
            public void handleField(String name, Receiver receiver) {
                ObjectHandler.this.handleField(name, receiver);
            }

            public U handleEnd() {
                return function.apply(ObjectHandler.this.handleEnd());
            }
        };
    }

    class DictValue<T> implements ObjectHandler<Map<String, T>> {
        private final HashMap<String, T> map = new HashMap<>();
        private final ValueHandler<T> valueHandler;

        public DictValue(ValueHandler<T> valueHandler) {
            this.valueHandler = valueHandler;
        }

        public static DictValue<Object> basicDict() {
            return new DictValue<>(ValueHandler.ObjectValue.INSTANCE);
        }

        public void handleField(String name, Receiver receiver) {
            T elem = receiver.receive(this.valueHandler);
            this.map.put(name, elem);
        }

        public Map<String, T> handleEnd() {
            return map;
        }
    }
}
