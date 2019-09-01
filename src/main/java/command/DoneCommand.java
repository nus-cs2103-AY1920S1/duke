package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDone = tasks.getTasks().get(index - 1);
        taskDone.markAsDone();
        String result = "Nice! I've marked this task as done: \n"
                + taskDone.getTypeIcon() + taskDone.getStatusIcon() + " " + taskDone + "\n";
        return result;
    }
}
