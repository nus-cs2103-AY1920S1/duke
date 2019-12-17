package com.duke.command;

import com.duke.Storage;
import com.duke.task.Task;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a delete command to delete task from task list.
 */
public class DeleteCommand extends Command {

    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";

    private int index;
    private Storage storage;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param list  Input task list.
     * @param index Index from user input.
     */
    public DeleteCommand(TaskList list, int index) {
        super(list);
        this.index = index;
        super.task = this.list.getTask(index);
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Executes the command.
     *
     * @return Output shown to user.
     * @throws IOException If file is not found or cannot be read.
     */
    public String execute() throws IOException {
        super.list.deleteTask(index);
        storage.deleteText(super.task.toString());
        return stringifyTask(super.list, super.task, TASK_DELETED_MESSAGE);
    }

    @Override
    public Task getDeletedTask() {
        return super.list.getTask(index);
    }

    @Override
    public boolean isDeleteCommand() {
        return true;
    }
}
