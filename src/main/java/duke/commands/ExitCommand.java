package duke.commands;
import duke.managers.*;

public class ExitCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ExitCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;

        this.ui.printLine("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }

}
