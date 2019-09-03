package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

import static duke.ui.Messages.BYE_MESSAGE;

public class ByeCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        CommandResult result = new CommandResult(BYE_MESSAGE);
        result.setExit(true);
        return result;
    }
}
