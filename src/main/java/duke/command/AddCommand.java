package duke.command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command{
    private Task taskToAdd;

    public AddCommand(Task taskToAdd){
        super();
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.addTaskToList(this.taskToAdd);
        ui.messageUser("Got it. I've added this task:",
                taskToAdd.getTaskStatus(),
                "Now you have " + tasks.getSize()
                        + ((tasks.getSize() <= 1) ? " task" : " tasks") + " in the list.");
    }
}
