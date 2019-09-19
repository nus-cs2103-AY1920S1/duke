package duke.task;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Task is a class to represent a task. It contains a description of the task and its status
 * (whether the task has been completed).
 */
public class Task {
    /**
     * The description of the task.
     */
    public String description;
    /**
     * A boolean describing whether the task has been completed.
     */
    protected boolean isDone;
    private static final LocalDateTime dateCreated = LocalDateTime.now();


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method that returns the tick or cross symbol, depending whether the task has been completed.
     * @return A tick or cross symbol, depending on whether the task has been completed.
     */
    public String getStatusIcon() {
        return isDone ? "Y" : "N"; //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public static Comparator<Task> typeComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.toString().compareTo(t2.toString());
        }
    };
}