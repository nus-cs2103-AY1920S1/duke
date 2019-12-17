package com.duke;

import com.duke.task.Task;

import java.util.Stack;

public class RecycleBin {
    private Stack<String> commandStrings;
    private Stack<Task> deletedTasks;

    RecycleBin() {
        this.commandStrings = new Stack<>();
        this.deletedTasks = new Stack<>();
    }

    /**
     * Gets the last command in String.
     *
     * @return String representation of command.
     */
    public String getCommandString() {
        return commandStrings.pop();
    }

    /**
     * Gets the last deleted task.
     *
     * @return The last deleted task.
     */
    public Task getDeletedTask() {
        return deletedTasks.pop();
    }

    void addCommandString(String command) {
        commandStrings.push(command);
    }

    void addDeletedTasks(Task task) {
        deletedTasks.push(task);
    }
}
