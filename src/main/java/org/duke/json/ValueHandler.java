package org.duke.json;

import java.util.ArrayList;
import java.util.Map;

public interface ValueHandler<T> {
    default T handleBoolean(boolean val) {
        throw new UnsupportedOperationException();
    }

    default T handleNumber(double val) {
        throw new UnsupportedOperationException();
    }

    default T handleNull() {
        throw new UnsupportedOperationException();
    }

    default T handleString(String val) {
        throw new UnsupportedOperationException();
    }

    default ObjectHandler<? extends T> handleObject() {
        throw new UnsupportedOperationException();
    }

    default ArrayHandler<? extends T> handleArray() {
        throw new UnsupportedOperationException();
    }

    class BooleanValue implements ValueHandler<Boolean> {
        public static final BooleanValue INSTANCE = new BooleanValue();

        public Boolean handleString(boolean val) {
            return val;
        }
    }

    class StringValue implements ValueHandler<String> {
        public static final StringValue INSTANCE = new StringValue();

        public String handleString(String val) {
            return val;
        }
    }

    class ObjectValue implements ValueHandler<Object> {
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

    static <T> ValueHandler<ArrayList<T>> listOf(ValueHandler<T> elemHandler) {
        return new ValueHandler<>() {
            public ArrayHandler<ArrayList<T>> handleArray() {
                return new ArrayHandler.ListValue<T>(elemHandler);
            }
        };
    }
}
