package duke.task;

import duke.exception.DukeException;

public class Task {
    protected static final String gap = "  ";
    protected String desc;
    protected boolean isDone;

    /**
     * Initialize a Task object.
     *
     * @param desc User inputted description.
     * @param done Optional, true if task is done, default is false.
     */
    public Task(String desc, boolean... done) {
        this.desc = desc;
        this.isDone = false;

        if (done.length == 1) {
            this.isDone = done[0];
        }
    }

    /**
     * Parse data string to find a task.
     *
     * @param input Input data string
     * @return Task found in data string
     * @throws DukeException If task cannot be created from input data string
     */
    public static Task parseFromData(String input) throws DukeException {
        String[] inputs = input.split(gap, 3);
        String type = inputs[0];
        boolean isDone = inputs[1].equals("1");
        Task task;
        switch (type) {
        case "T":
            task = Todo.parseFromData(input, isDone);
            break;
        case "D":
            task = Deadline.parseFromData(input, isDone);
            break;
        case "E":
            task = Event.parseFromData(input, isDone);
            break;
        default:
            throw new DukeException("Invalid data");
        }
        return task;
    }

    /**
     * Getter method for variable desc.
     *
     * @return Task description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for variable desc.
     *
     * @param desc Task description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter method for done variable.
     *
     * @return True if task is done, false if task is not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Label task done status as requested in input.
     *
     *
     * @param done Done state of task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Label task as done.
     */
    public void setDone() {
        setDone(true);
    }

    /**
     * Label task as not done.
     */
    public void setNotDone() {
        setDone(false);
    }

    /**
     * Returns done status of task.
     *
     * @return Tick if done, cross if not done.
     */

    public String getDoneStatus() {
        return isDone() ? "O" : "X"; //"\u2714" : "\u2718";
    }

    public String toStorageString() {
        return (isDone() ? "1" : "0") + gap + getDesc();
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + getDesc();
    }

}
