package duke.command;

import duke.DukeUtil;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.TaskUtil;

import java.util.Arrays;

class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.deadline;
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        int byIndex = DukeUtil.getIndexOfPattern(commandArgs, "/by");
        if (byIndex == -1) {
            throw new DukeInvalidArgumentException(
                    "Missing /by delimiter for deadline command",
                    " \u2639 OOPS!!! I dont know what is your deadline!\n"
                            + " You should add a deadline with\n"
                            + " \'deadline <description> /by <timing>\'");
        }

        String description = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, 0, byIndex),
                " ");
        String timing = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, byIndex + 1, commandArgs.length),
                " ");

        TaskUtil.validateTaskDescription(description);

        taskToAdd = new DeadlineTask(description, timing);
    }
}
