package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

class DoneCommand extends WritableCommand {
    private Task task;

    public DoneCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.done;
    }

    @Override
    void run(TaskList tasks, Ui ui, Storage storage) {
        task.setDone(true);
        ui.printMsgLine(" Nice! I've marked this task as done:");
        ui.printMsgLine(String.format("   %s", task.getStatusText()));
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length > 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after done command",
                    " \u2639 OOPS!!! There shouldn't be so many arguments!");
        }

        try {
            int taskIndex = Integer.parseInt(commandArgs[0]);
            task = tasks.getTaskByIndex(--taskIndex);

            if (task.isDone()) {
                throw new DukeInvalidArgumentException(
                        "User specified task is already marked as done",
                        " \u2639 OOPS!!! The task you gave me was already marked as done!");
            }
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(
                    "Could not parse argument supplied into a list index",
                    " \u2639 OOPS!!! The task number you gave me wasn't a valid number,\n"
                            + " or you didn't give me one at all!");
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            throw new DukeInvalidArgumentException(
                    "User number supplied was out of list bounds",
                    " \u2639 OOPS!!! The task number you gave me wasn't within your\n"
                            + " current list!");
        }
    }
}
