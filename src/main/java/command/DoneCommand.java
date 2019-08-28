package command;

import task.Task;
import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.done(index);

        //display successful message
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
    }
}