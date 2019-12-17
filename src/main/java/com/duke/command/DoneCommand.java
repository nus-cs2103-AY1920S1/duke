package com.duke.command;

import com.duke.Storage;
import com.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a done command to indicate that a task is done.
 */
public class DoneCommand extends Command {
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";

    private Storage storage;

    /**
     * Constructs a DoneCommand object.
     *
     * @param list  Input task list.
     * @param index Index from user input.
     */
    public DoneCommand(TaskList list, int index) {
        super(list);
        super.task = this.list.getTask(index);
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Executes the command.
     *
     * @return Output shown to user.
     * @throws IOException If file not found or cannot be read.
     */
    public String execute() throws IOException {
        String initialTask = super.task.toString();
        super.task.markAsDone();
        storage.overwriteText(initialTask, super.task.toString());
        return TASK_DONE_MESSAGE + "\n" + task.toString();
    }
}
