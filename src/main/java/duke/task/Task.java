package duke.task;

import duke.Duke;
import duke.DukeException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    protected abstract String getTypeNameWithQuantifier();

    public abstract String toExportFormat();

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
            t = new Event(stringArray[2], Duke.parseDate(stringArray[3]));
            if (stringArray[1].equals("1")) {
                t.markAsDone();
            }
            return t;
        case "D":
            t = new Deadline(stringArray[2], Duke.parseDate(stringArray[3]));
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
