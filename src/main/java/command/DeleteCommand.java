package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Executes deletion.
     *
     * @param tasks is TaskList of tasks
     *
     * @param ui returns strings
     *
     * @param storage is for output
     *
     * @return the final string command that has to be printed
     */
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            assert val <= (tasks.size()) : "Enter a smaller number";
            String result = ui.deleteMessageFX(val - 1, tasks);
            tasks.remove(val - 1);
            storage.updateFile(tasks, expenses);
            return result;
        } catch (AssertionError f) {
            return f.getMessage();
        } catch (Exception e) {
            return "File not found";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
