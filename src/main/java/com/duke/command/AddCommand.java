package com.duke.command;

import com.duke.Storage;
import com.duke.TaskList;

import java.io.IOException;

public class AddCommand extends Command {

    private Storage storage;
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

    AddCommand(TaskList list) {
        super(list);
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Executes the command.
     *
     * @return Response of Duke.
     * @throws IOException If file is not found or cannot be read.
     */
    @Override
    public String execute() throws IOException {
        super.list.addTask(super.task);
        this.storage.appendText(super.task.toString());
        return stringifyTask(super.list, super.task, TASK_ADDED_MESSAGE);
    }
}
