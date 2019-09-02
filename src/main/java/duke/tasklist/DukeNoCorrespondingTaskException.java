package duke.tasklist;

import duke.error.DukeException;

/**
 * Exception to be thrown when an error occurs because a command to modify a task, which is not in the task list,
 * is executed.
 */
public class DukeNoCorrespondingTaskException extends DukeException {
    /**
     * Constructs the exception to be thrown
     *
     * @param number The number that does not match a task number in the task list
     */
    public DukeNoCorrespondingTaskException(int number) {
        super("The following number does not correspond to a task in the list: \n", Integer.toString(number));
    }
}
