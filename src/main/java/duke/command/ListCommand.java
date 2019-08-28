package duke.command;

import duke.task.Task;

/**
 * Represents a {@link Command} to display a list of {@link Task}.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute() {
        ui.showLine();
        if (tasks.size() == 0) {
            ui.showMessage("There are no tasks in your list");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                ui.showMessage(i + ". " + task);
            }
        }
        ui.showLine();
    }
}
