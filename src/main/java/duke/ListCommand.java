package duke;

public class ListCommand extends Command {
    ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showTasks(tasks);
    }
}
