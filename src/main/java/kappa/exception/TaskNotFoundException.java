package kappa.exception;

/**
 * Exception for when task cannot be found in list.
 */
public class TaskNotFoundException extends KappaException {

    /**
     * Constructor for TaskNotFoundException.
     *
     * @param task Description of task that cannot be found.
     */
    public TaskNotFoundException(String task) {
        super(String.format("%s cannot be found.", task));
    }
}
