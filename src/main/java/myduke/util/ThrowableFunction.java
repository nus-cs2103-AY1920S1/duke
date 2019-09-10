package myduke.util;

import myduke.exception.DukeException;


/**
 * A Functional Interface for a throwable Function.
 *
 * @param <T> the argument type.
 * @param <R> the return type.
 * @param <E> the throwable exception type.
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, E extends Exception> {
    R accept(T t) throws DukeException;
}
