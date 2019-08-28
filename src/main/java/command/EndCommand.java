package command;

import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class EndCommand extends Command {
    public EndCommand() {
        super.isExit = true;
    }

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //display goodbye message
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
