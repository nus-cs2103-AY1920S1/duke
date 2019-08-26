package duke.commands;
import duke.exceptions.*;
import duke.managers.*;
import java.io.IOException;

public class ListCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ListCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;

        this.ui.printLine("Here are the tasks in your list:");
        int maxNum = tasks.totalNumTasks();
        for (int i = 1; i <= maxNum; i++) {
            this.ui.printLine(i + "." + tasks.getTask(i));
        }
    }

    public boolean isExit() {
        return false;
    }
}
