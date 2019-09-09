package seedu.duke.ui;

import seedu.duke.DukeException;

/**
 * Functional interface to help implement lambdas.
 */
public interface DukeErrorInterface {
    /**
     * Returns the error String prompt.
     *
     * @param e DukeException object, which is a subclass of the Exception class.
     */
    public abstract String getLoadingError(DukeException e);
}
