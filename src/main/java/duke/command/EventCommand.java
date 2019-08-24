package duke.command;

import duke.exception.DukeException;
import duke.shared.GetArgumentsUtil;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends AddCommand {
    private String[] commands;

    public EventCommand(String[] commands) {
        this.commands = commands;
    }

    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes Event command.
     * @param taskList TaskList object for the duke program
     * @param ui ui object for the duke program
     * @param storage storage object for the duke program
     * @return true if the command executes successfully, else false
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String[] args = GetArgumentsUtil.getTwoCommandArgs(1, "/at", commands);
            Task eventTask = new Event(args[0], args[1]);
            taskList.addToTaskList(eventTask);
            ui.showMessage( Messages.ADDED_TASK_MESSAGE, Messages.COMMAND_INDENTATION +
                    Messages.COMPLETION_INDENTATION + eventTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
            return true;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }
}
