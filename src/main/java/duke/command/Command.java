package duke.command;

import duke.task.TaskList;

public abstract class Command {

    public abstract Commands getCommandType();

    /**
     * Execute command on given task and save into tasklist.
     */
    public abstract String execute(TaskList taskList);
}
