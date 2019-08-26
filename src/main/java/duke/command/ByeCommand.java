package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

class ByeCommand extends Command {
    public ByeCommand(String[] args) {
        super(args);
        commandType = Commands.bye;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsgLine(" Bye. Hope to see you again soon!");
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length >= 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after bye command",
                    " \u2639 OOPS!!! There shouldn't be anything following 'bye',\n"
                            + " are you sure you wanted to exit?");
        }
    }
}
