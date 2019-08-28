package command;

import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        tasks.list();
    }
}