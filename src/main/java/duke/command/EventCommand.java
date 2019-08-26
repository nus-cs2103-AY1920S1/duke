package duke.command;

import duke.DukeUtil;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeInvalidArgumentException;
import duke.task.EventTask;
import duke.task.TaskUtil;

import java.util.Arrays;

public class EventCommand extends AddTaskCommand {
    public EventCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.event;
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        int atIndex = DukeUtil.getIndexOfPattern(commandArgs, "/at");
        if (atIndex == -1) {
            throw new DukeInvalidArgumentException(
                    "Missing /at delimiter for event command",
                    " \u2639 OOPS!!! I dont know what is your event timing!\n"
                            + " You should add a time with\n"
                            + " \'event <description> /at <time>\'");
        }

        String description = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, 0, atIndex),
                " ");
        String timing = DukeUtil.concatStrings(
                Arrays.copyOfRange(commandArgs, atIndex + 1, commandArgs.length),
                " ");

        TaskUtil.validateTaskDescription(description);
        EventTask eventTask = new EventTask(description, timing);

        taskToAdd = eventTask;
    }
}
