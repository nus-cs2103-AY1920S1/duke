package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

class DeleteCommand extends WritableCommand {
    private int taskIndex;

    public DeleteCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.delete;
    }

    @Override
    void run(TaskList tasks, Ui ui, Storage storage) {
        Task taskDeleted = tasks.deleteTaskByIndex(taskIndex);

        ui.printMsgLine(" Noted. I've removed this task:");
        ui.printMsgLine(String.format("   %s", taskDeleted.getStatusText()));
        ui.printMsgLine(String.format(" Now you have %d tasks in the list.", tasks.getSize()));
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length > 2) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after delete command",
                    " \u2639 OOPS!!! There shouldn't be so many arguments!");
        }

        try {
            taskIndex = Integer.parseInt(commandArgs[0]);
            tasks.getTaskByIndex(--taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(
                    "Could not parse argument supplied into a list index",
                    " \u2639 OOPS!!! The task number you gave me wasn't a valid number,\n"
                            + " or you didn't give me one at all!");
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeInvalidArgumentException(
                    "User number supplied was out of list bounds",
                    " \u2639 OOPS!!! The task number you gave me wasn't within your current list!");
        }
    }
}
