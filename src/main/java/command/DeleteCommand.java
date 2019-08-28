package command;

import task.Task;
import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.remove(index);

        //display successful message and task count
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}