package duke.support;

/**
 * This is a functional interface and can be used for a lamda expression that is used for printing messages.
 */
@FunctionalInterface
public interface PrintFunction {
    /**
     * Print the things specified in the implementation of this method.
     * The implementation specifies what and how things should be printed.
     */
    void print();
}
