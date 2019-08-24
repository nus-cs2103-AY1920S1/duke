import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command {
    private int ordering;

    DeleteCommand(int ordering) {
        super();
        this.ordering = ordering;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException, IOException {
        Task task = taskList.getTask(ordering);
        taskList.remove(ordering);
        int numOfTasks = taskList.getSize();
        ui.echo("Noted. I've removed this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
        storage.taskListToFile();
    }

    @Override
    boolean shouldExit() {
        return false;
    }
}
