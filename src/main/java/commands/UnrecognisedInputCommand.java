package commands;

import components.Storage;
import components.Ui;
import components.TaskList;

public class UnrecognisedInputCommand implements Command{
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.print("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
