package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //Mark task as done
            System.out.println(tasks.deleteTask(input));
        } catch (NullPointerException err) {
            System.out.println(ui.invalidEntry());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
