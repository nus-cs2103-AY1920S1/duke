package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Shows the list of available commands.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine("You may use the following commands:");
        ui.printLine("bye - Exit the program");
        ui.printLine("deadline [description] /by [date: dd/MM/YYYY] - Add deadline");
        ui.printLine("delete [task number] - Delete a task");
        ui.printLine("done [task number] - Marks a task as done");
        ui.printLine("event [description] /at [date: dd/MM/YYYY] - Add event");
        ui.printLine("find [keywords] - Finds a task based on its keywords");
        ui.printLine("help - Show help");
        ui.printLine("list - List all tasks");
        ui.printLine("todo [description] - Add a todo");
    }
}
