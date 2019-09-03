package org.duke.json;

/**
 * Represents a way to construct any value, while reading a JSON value.
 */
@FunctionalInterface
public interface Receiver {
    /**
     * Given a handler extracting a {@param <T>} value, run it in a context of a JSON value.
     *
     * @param handler Value extractor
     * @param <T>     Return type
     * @return Extracted value
     */
    <T> T receive(ValueHandler<? extends T> handler);
}
