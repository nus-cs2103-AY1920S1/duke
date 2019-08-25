package duke.tasklist;

import duke.DukeException;

public class DukeEmptyListException extends DukeException {
    public DukeEmptyListException() {
        super("Your list is empty!");
    }
}
