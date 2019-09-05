package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class DoneCommand extends Command {

    private String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //Mark task as done
            tasks.setDone(this.input);
        } catch (NullPointerException err) {
            ui.invalidEntry();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
