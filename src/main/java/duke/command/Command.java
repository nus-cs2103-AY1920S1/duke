package duke.command;

import duke.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks);

    protected String createTotalNumOfTaskMsg(TaskList tasks) {
        return "Now you have " + tasks.getSizeOfTaskList() + " tasks in the list.";
    }
}
