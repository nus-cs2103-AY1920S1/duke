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
}
