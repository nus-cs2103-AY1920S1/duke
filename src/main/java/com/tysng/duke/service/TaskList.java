package com.tysng.duke.service;

import com.tysng.duke.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles CRUD operations to the task list in the Application.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Construct the task list with a given list of tasks.
     *
     * @param tasks the specified list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Gets the task at the given index.
     *
     * @param index the index at which the task is located
     * @return the wanted task
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index the index at which the task is located
     * @return the removed task
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }
}
