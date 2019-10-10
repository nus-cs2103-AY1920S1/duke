package com.leeyiyuan.task;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a list of Tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructs a TaskList from a list of Tasks.
     */
    public TaskList(Collection<? extends Task> tasks) {
        super(tasks);
    }

    /**
     * Checks whether the TaskList contains a Task with a given title.
     *
     * @param title Title to check for.
     * @return True if TaskList contains Task with the given title.
     */
    public boolean containsTitle(String title) {
        for (Task task : this) {
            if (task.getTitle().toUpperCase().equals(title.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
