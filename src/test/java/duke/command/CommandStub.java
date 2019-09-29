package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.MainWindow;

class CommandStub extends Command {

    boolean didRun = false;
    boolean didValidate = false;

    CommandStub(String[] commandArgs) {
        super(commandArgs);
    }

    @Override
    void run(TaskList tasks, MainWindow ui, Storage storage) {
        didRun = true;
    }

    @Override
    void validate(TaskList tasks, MainWindow ui, Storage storage) throws DukeInvalidArgumentException {
        didValidate = true;
    }
}
