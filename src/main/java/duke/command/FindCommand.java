package duke.command;

import duke.DukeUtil;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.find;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsgLine(" Here are the matching tasks in your list:");
        String searchPattern = DukeUtil.concatStrings(commandArgs, " ");

        int taskIndex = 1;
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().contains(searchPattern)) {
                ui.printMsgLine(String.format(" %d.%s", taskIndex, task.getStatusText()));
                taskIndex++;
            }
        }
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        //no validation required, an search for an empty string is still a valid search!
    }
}
