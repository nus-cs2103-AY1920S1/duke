package seedu.duke.core;

/**
 * Functional interface to help implement lambdas.
 */
public interface DukeErrorInterface {
    /**
     * Abstract method to get the loading error String.
     *
     * @param e DukeException object, which is a subclass of the Exception class.
     * @return String.
     */
    public abstract String getLoadingError(DukeException e);
}
