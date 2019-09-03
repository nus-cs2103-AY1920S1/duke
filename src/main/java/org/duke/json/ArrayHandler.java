package org.duke.json;

import java.util.ArrayList;

public interface ArrayHandler<T> {
    void handleElement(Receiver receiver);

    T handleEnd();

    static <T> ValueHandler<ArrayList<T>> listOf(ValueHandler<T> valueHandler) {
        return new ValueHandler<ArrayList<T>>() {
            public ArrayHandler<ArrayList<T>> handleArray() {
                return new ListValue<>(valueHandler);
            }
        };
    }

    class ListValue<T> implements ArrayHandler<ArrayList<T>> {
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
