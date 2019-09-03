package task;

import logic.DukeException;

public abstract class Task {
    String description;
    boolean isDone;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Marks Task as done.
     *
     * @throws DukeException If task is already done
     */
    public void markDone() throws DukeException {
        if (isDone) {
            throw new DukeException("task.task is already done!");
        } else {
            this.isDone = true;
        }
    }

    public String getDescription() {
        return description;
    }

    /**
     * Converts to String to be written onto text file.
     * @return String
     */
    public abstract String toFileString();

    public abstract String toString();
}