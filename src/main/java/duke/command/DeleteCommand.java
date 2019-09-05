package duke.command;

import duke.Task;
import duke.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] parts) {
        super(parts);
    }

    @Override
    public void execute(TaskList tasks) {
        // Delete
        int itemId = Integer.parseInt(parts[1]);
        Task currentTask = tasks.get(itemId - 1);
        tasks.remove(currentTask);

        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
