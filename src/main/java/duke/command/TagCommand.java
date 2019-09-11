package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class TagCommand extends Command {

    public TagCommand(String commandContent) {
        
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.addToOutputs("Not implemented yet");
    }
}
