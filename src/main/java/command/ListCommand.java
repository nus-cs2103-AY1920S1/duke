package command;

import task.TaskList;
import duke.UserInterface;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        System.out.println("\tHere are the tasks in your list:");
        tasks.list();
    }
}