package com.duke.command;

import com.duke.task.Task;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Represents command to be executed by Duke.
 */
public abstract class Command {
    static final String FILE_PATH = "data/duke.txt";
    protected TaskList list;
    protected Task task;

    /**
     * Constructs a Command object.
     *
     * @param taskList Input task list.
     */
    public Command(TaskList taskList) {
        this.list = taskList;
    }

    /**
     * Constructs a Command object with default constructor.
     */
    public Command() {
    }

    /**
     * Executes the command.
     *
     * @return Response of Duke.
     * @throws IOException If file is not found or cannot be read.
     */
    public abstract String execute() throws IOException;

    String stringifyTask(TaskList list, Task task, String firstLine) {
        String lastLine = "Now you have" + " " + list.size() + " " + "tasks in the list.";
        return firstLine + "\n" + task.toString() + "\n" + lastLine;
    }

    /**
     * Checks whether the Command is a ByeCommand.
     *
     * @return True if it is a ByeCommand, false otherwise.
     */
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Checks whether the Command is a DeleteCommand.
     *
     * @return True if it is a DeleteCommand, false otherwise.
     */
    public boolean isDeleteCommand() {
        return false;
    }

    /**
     * Checks whether the Command is a UndoCommand.
     *
     * @return True if it is a UndoCommand, false otherwise.
     */
    public boolean isUndoCommand() {
        return false;
    }

    /**
     * Gets deleted task.
     *
     * @return Deleted task.
     */
    public Task getDeletedTask() {
        return new Task("");
    }
}
