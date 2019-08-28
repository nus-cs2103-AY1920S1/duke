package command;

import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class EndCommand extends Command {
    public EndCommand() {}

    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //stop the while loop of actions
        isExit = true;

        //display goodbye message
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
