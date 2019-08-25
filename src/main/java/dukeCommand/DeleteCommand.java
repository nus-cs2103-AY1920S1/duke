package dukeCommand;

import dukeCore.DukeException;
import dukeHelper.Storage;
import dukeHelper.Ui;
import dukeTask.Task;
import dukeTask.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(String filePath, String[] inputSplit) {
        super(filePath, inputSplit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (inputSplit.length != 2) {
                // Exception if there is no task number or multiple words after "delete"
                throw new DukeException(ui.separationLine
                        + "\n     \u2639 OOPS!!! Please specify number of a single task to delete.\n"
                        + ui.separationLine + "\n");
            }
            int specifiedDel = Integer.parseInt(inputSplit[1]); // will throw NumberFormatException if not int
            if (specifiedDel < 1 || specifiedDel > tasks.getSize()) {
                // Exception if task number is beyond current number of tasks
                throw new DukeException(ui.separationLine
                        + "\n     \u2639 OOPS!!! Please specify valid task number.\n" + ui.separationLine + "\n");
            }
            Task delTask = tasks.getElement(specifiedDel - 1);
            tasks.deleteFromList(specifiedDel - 1);
            ui.printDeleteNotification(delTask.toString(), tasks.getSize());

            // Write to file
            storage.overwriteFile(tasks.toArrayList());
        } catch (NumberFormatException ne) {
            ui.printError(ui.separationLine + "\n     \u2639 OOPS!!! Please specify task number as one integer only.\n"
                    + ui.separationLine + "\n");
        }
    }
}
