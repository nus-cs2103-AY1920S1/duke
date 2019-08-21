package duke.commands;

import duke.Duke;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private static final String DELETE_TASK_STRING = "Noted. I've removed this task:";

    private int index;

    public DeleteCommand(Duke duke, String input) {
        super(duke, input);
        String[] args = input.split(" ");
        this.index = Integer.parseInt(args[1]);
    }

    public void execute() {
        Task deletedTask = duke.getTasks().getTask(index);
        duke.getTasks().deleteTask(index);
        duke.say(
                String.format("%s\n\t%s\nNow you have %d tasks in the list.",
                        DELETE_TASK_STRING, deletedTask, duke.getTasks().numTasks())
        );
    }
}
