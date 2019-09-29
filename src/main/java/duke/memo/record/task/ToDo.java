package duke.memo.record.task;

import duke.memo.exception.DukeException;
import duke.memo.record.task.Task;

public class ToDo extends Task {
    private static final String TASK_TYPE = "todo";

    /**
     * Constructor for ToDo Task.
     * Throw error if there is no description.
     *
     * @param desc  Description for the ToDo Task.
     * @throws DukeException  If detail is missing.
     */
    public ToDo(String desc) throws DukeException {
        super(desc, TASK_TYPE);
    }

    /**
     * Constructor for ToDo Task.
     * Throw error if there is no description.
     *
     * @param taskDetails  Details for the Deadline Task.
     * @throws DukeException  If detail is missing.
     */
    public ToDo(String[] taskDetails) throws DukeException {
        super(taskDetails[2], TASK_TYPE);
        this.isDone = taskDetails[1].equalsIgnoreCase("1");
    }

    @Override
    public String getPrintableMsg() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
