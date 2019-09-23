package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidTaskDukeException;

public class DoAfter extends Task {

    private String afterWhen;

    /**
     * Constructor of Task.
     *
     * @param name Sets name of Task to input.
     */
    public DoAfter(String name, String afterWhen) throws EmptyTaskDukeException, InvalidTaskDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("doafter");
        }
        if (afterWhen == null) {
            throw new InvalidTaskDukeException("doafter");
        }
        this.afterWhen = afterWhen;
    }

    /**
     * Accessor to get due date (afterWhen) of DoAfter.
     *
     * @return
     */
    public String getAfterWhen() {
        return afterWhen;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[DA]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (");
        stringBuilder.append(DateTime.create(afterWhen));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
