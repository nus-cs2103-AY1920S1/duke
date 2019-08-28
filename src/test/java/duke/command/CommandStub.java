package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

class CommandStub extends Command {

    boolean didRun = false;
    boolean didValidate = false;

    CommandStub(String[] commandArgs) {
        super(commandArgs);
    }

    @Override
    void run(TaskList tasks, Ui ui, Storage storage) {
        didRun = true;
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        didValidate = true;
    }
}
