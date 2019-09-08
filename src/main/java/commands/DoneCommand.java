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
            System.out.println(tasks.setDone(this.input));
        } catch (NullPointerException err) {
            System.out.println(ui.invalidEntry());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
