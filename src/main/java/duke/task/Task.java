package duke.task;

import duke.DateUtil;
import duke.Duke;
import duke.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description the description of the task.
     * @throws DukeException if no description is supplied.
     */
    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task, representing whether the task is done.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    protected abstract String getTypeNameWithQuantifier();

    /**
     * Converts a task to its export format.
     *
     * @return the converted string in export format.
     */
    public abstract String toExportFormat();

    /**
     * Constructs a task from imported raw string.
     *
     * @param text the raw string to be imported.
     * @return the task constructed from the raw string.
     * @throws DukeException if the raw string cannot be converted to a task.
     */
    public static Task fromImportFormat(String text) throws DukeException {
        String[] stringArray = text.split(" \\| ");
        Task t;
        switch (stringArray[0]) {
        case "T":
            t = new Todo(stringArray[2]);
            if (stringArray[1].equals("1")) {
                t.markAsDone();
            }
            return t;
        case "E":
            t = new Event(stringArray[2], DateUtil.parseDate(stringArray[3]));
            if (stringArray[1].equals("1")) {
                t.markAsDone();
            }
            return t;
        case "D":
            t = new Deadline(stringArray[2], DateUtil.parseDate(stringArray[3]));
            if (stringArray[1].equals("1")) {
                t.markAsDone();
            }
            return t;
        default:
            throw new DukeException("Invalid format");
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
