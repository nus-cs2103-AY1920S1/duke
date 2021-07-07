package myduke.util;

import java.util.HashMap;

/**
 * A HashMap class which maps a key to a throwable function type.
 *
 * @param <K> the key type.
 * @param <T> the argument type for the throwable function.
 * @param <R> the return type for the throwable function.
 * @param <E> the exception type for the throwable function.
 */
public class ThrowableFunctionHashMap<K, T, R, E extends Exception> extends HashMap<K, ThrowableFunction<T, R, E>> {
}

