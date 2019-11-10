package tasks;

/**
 * Class for the deadline task.
 * Contains a description of the task and the datetime for which it is due.
 */
public class ToDo extends Task {

    public ToDo(String des) {
        super(des);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return string consisting of the type of task (todo), done marker, description and due
     *         date and time
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Clones the task and returns the new copy.
     *
     * @return cloned task with identical description and done status
     */
    @Override
    public ToDo clone() {
        ToDo task = new ToDo(description);
        if (isDone) {
            task.markDone();
        }
        return task;
    }
}