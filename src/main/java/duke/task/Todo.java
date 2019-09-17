package duke.task;

import duke.exception.DukeException;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * Throws a DukeException if the description is empty.
     *
     * @param description The description of the Todo Task.
     * @throws DukeException Exception thrown if there is an issue creating the Todo.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.equals("")) {
            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
        }
        this.taskType = "T";
    }

    /**
     * Constructor for Todo that allows setting of the isDone boolean.
     * This is mainly used when parsing a String from storage into a Todo Task.
     *
     * @param description The description of the todo task
     * @param isDone      Whether the task is done
     * @throws DukeException thrown when there is an error creating the todo.
     */
    public Todo(String description, boolean isDone) throws DukeException {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Marks a Todo task as done.
     * Throws a DukeException if the Todo is already marked as done.
     * We return the task so that we can get its updated status more easily.
     *
     * @return the Todo that has been marked as done
     * @throws DukeException if there is an error marking the Todo as done
     */
    public Task markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException(":( OOPS!!! The todo is already marked as done.");
        }
        Todo completed = new Todo(this.description);
        completed.isDone = true;
        return completed;
    }

    /**
     * Gets the Todo status as a string.
     * The string returned is formatted as specified on the CS2103T website.
     *
     * @return the task status as a string.
     */
    public String getTaskStatus() {
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "]" + this.getTaskDescription());
    }

    /**
     * Gets the Todo status a string, formatted for storage in the .txt file.
     * The string returned is formatted for storage as specified on the CS2103T website.
     *
     * @return the task status as a string for storage.
     */
    public String getStoredTaskStatus() {
        return (this.getTaskType() + " | " + this.getStatusIcon() + " | " + this.getTaskDescription());
    }
}
