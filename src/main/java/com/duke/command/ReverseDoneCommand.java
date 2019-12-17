package com.duke.command;

import com.duke.Storage;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a command which is the 'flip' version of DoneCommand.
 */
public class ReverseDoneCommand extends Command {
    private static final String TASK_NOT_DONE_MESSAGE = "Nice! I've marked this task as not done:";

    private Storage storage;

    /**
     * Constructs a ReverseDoneCommand object.
     *
     * @param list  Input task list.
     * @param index Index from user input.
     */
    public ReverseDoneCommand(TaskList list, int index) {
        super(list);
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
        String initialTask = super.task.toString();
        super.task.markAsNotDone();
        storage.overwriteText(initialTask, super.task.toString());
        return TASK_NOT_DONE_MESSAGE + "\n" + task.toString();
    }
}
