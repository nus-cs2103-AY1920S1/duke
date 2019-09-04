package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasklist) {
        return tasklist.getPrintListMessage();
    }
}
