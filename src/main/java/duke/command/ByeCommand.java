package duke.command;

import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String[] message) {
        super(message);
    }

    @Override
    public String execute(TaskList tasks) {
        Storage.save(tasks);
        String response = "Bye. Hope to see you again soon!";
        return response;
    }
}
