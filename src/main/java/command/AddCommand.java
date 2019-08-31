package command;

import task.Task;
import task.TaskList;
import duke.UserInterface;
import duke.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //add task into the task list
        tasks.add(task);

        //display successful message and task count
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
