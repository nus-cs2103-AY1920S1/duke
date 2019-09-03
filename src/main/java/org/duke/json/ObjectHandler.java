package org.duke.json;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface ObjectHandler<T> {
    void handleField(String name, Receiver receiver);

    T handleEnd();

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

    static <T> ValueHandler<Map<String, T>> mapOf(ValueHandler<T> valueHandler) {
        return new ValueHandler<>() {
            public ObjectHandler<Map<String, T>> handleObject() {
                return new DictValue<>(valueHandler);
            }
        };
    }

    class DictValue<T> implements ObjectHandler<Map<String, T>> {
        private final HashMap<String, T> map = new HashMap<>();
        private final ValueHandler<T> valueHandler;

        public static DictValue<Object> basicDict() {
            return new DictValue<>(ValueHandler.ObjectValue.INSTANCE);
        }

        public DictValue(ValueHandler<T> valueHandler) {
            this.valueHandler = valueHandler;
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
