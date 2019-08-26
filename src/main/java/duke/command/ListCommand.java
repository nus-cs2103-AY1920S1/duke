package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

class ListCommand extends Command {
    public ListCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.list;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsgLine(" Here are the tasks in your list:");

        int taskIndex = 1;
        for (Task task : tasks.getAllTasks()) {
            ui.printMsgLine(String.format(" %d.%s", taskIndex, task.getStatusText()));
            taskIndex++;
        }
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length > 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after list command",
                    " \u2639 OOPS!!! There shouldn't be anything following 'list',\n"
                            + " did you meant to do something else?");
        }
    }
}
