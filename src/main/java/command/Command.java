package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public abstract class Command {
    protected String command;

    Command(String command) {
        this.command = command;
    }

    public abstract String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses)
                                                                                        throws Exception;

    public abstract boolean isExit();
}
