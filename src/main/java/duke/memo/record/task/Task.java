package duke.memo.record.task;

import duke.memo.exception.DukeException;
import duke.memo.record.Record;

public abstract class Task extends Record {

    /**
     * Constructor for Task.
     * Throw error if there is no time description or wrong format.
     *
     * @param desc  Description for the Task.
     * @param taskType  Task type for the Task.
     * @throws DukeException  If details missing or in wrong format.
     */
    public Task(String desc,String taskType) throws DukeException {
        super(desc, taskType);
    }

    public Task() {
        super();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
