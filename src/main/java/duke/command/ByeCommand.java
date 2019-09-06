package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String[] message) {
        super(message);
    }

    @Override
    public String execute(TaskList tasks) {
        //Duke.isExiting = true; change to exit gui
        Storage.save(tasks);
        String response = "Bye. Hope to see you again soon!";
        return response;
    }
}
