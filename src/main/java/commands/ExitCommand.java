package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ExitCommand extends Command {

    public ExitCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Bye. Hope to see you again soon!");
    }

}
