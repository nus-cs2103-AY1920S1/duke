package command;

import util.Storage;
import util.TaskList;
import util.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) {
        System.out.println("Invalid Command, try again.");
    }
}
