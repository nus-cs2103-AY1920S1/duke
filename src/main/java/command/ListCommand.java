package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    /**
     * executes the printing of list.
     *
     * @param tasks is the taskList of  tasks
     *
     * @param ui prints the output
     *
     * @param storage manages the output file
     *
     * @return the output String
     */
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) {
        return ui.listCommandFX(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
