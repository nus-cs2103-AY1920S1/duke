package commands;

import storage.Storage;
import ui.Ui;
import tasks.TaskList;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //Mark task as done
            return (tasks.deleteTask(input));
        } catch (NullPointerException err) {
            return (ui.invalidEntry());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
