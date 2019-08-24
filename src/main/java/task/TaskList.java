package com.leeyiyuan.task;


import java.util.ArrayList;
import java.util.Collection;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList(Collection<? extends Task> tasks) {
        super(tasks);
    }
}
