package seedu.duke;

public class ListCommand extends Command {
    private String command;
    public ListCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.printAllTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}