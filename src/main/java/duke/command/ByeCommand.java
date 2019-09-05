package duke.command;

import duke.Duke;
import duke.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String[] message) {
        super(message);
    }

    @Override
    public void execute(TaskList tasks) {
        Duke.isExiting = true;
    }
}
