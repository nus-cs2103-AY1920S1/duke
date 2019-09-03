package duke.tasklist;

import duke.error.DukeException;

/**
 * A DukeException to be thrown when an error occurs because a Command tries to modify a Task which does
 * not exist in the TaskList.
 */
public class DukeNoCorrespondingTaskException extends DukeException {
    /**
     * Constructs the exception to be thrown when an error occurs because a Command tries to modify
     * a Task which does not exist in the TaskList.
     *
     * @param number The number which does not correspond to a Task in the TaskList
     */
    public DukeNoCorrespondingTaskException(int number) {
        super("The following number does not correspond to a task in the list: \n", Integer.toString(number));
    }
}
