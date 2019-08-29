package commands;

import components.Storage;
import components.Ui;
import components.TaskList;

public class PrintUserInputErrorCommand implements Command {
    private String message;

    public PrintUserInputErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Ui.printErr(message);
    }
}
