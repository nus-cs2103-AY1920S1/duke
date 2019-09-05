package duke.command;

import duke.Task;
import duke.TaskList;

public class DoneCommand extends Command {
    public DoneCommand(String[] parts) {
        super(parts);
    }

    @Override
    public void execute(TaskList tasks) {
        // Done
        int itemId = Integer.parseInt(parts[1]);
        Task currentTask = tasks.get(itemId - 1);
        currentTask.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n[1] " + currentTask.getName());
    }
}
