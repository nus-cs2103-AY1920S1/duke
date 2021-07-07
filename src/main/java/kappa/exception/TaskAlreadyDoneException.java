package kappa.exception;

/**
 * Exception for when task in list has already been marked as done.
 */
public class TaskAlreadyDoneException extends KappaException {

    /**
     * Constructor for TaskAlreadyDoneException.
     *
     * @param task description of task that has already been marked done.
     */
    public TaskAlreadyDoneException(String task) {
        super(String.format("%s already done!", task));
    }
}
