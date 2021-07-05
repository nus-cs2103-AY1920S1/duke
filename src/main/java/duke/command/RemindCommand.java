package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskType;

import java.util.HashMap;

public class RemindCommand extends Command {
    private TaskType type;

    public RemindCommand(TaskType type, boolean isExit) {
        super(isExit);
        this.type = type;
    }

    /**
     * Shows a reminder of tasks to do in order.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     * @return the response containing the response and boolean flag to exit the program.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        // create a new task list filtered to a specific task.
        HashMap<TaskType, TaskList> taskDict = taskList.filter(type);
        String response = ui.showRemindList(taskDict, type);
        return new CommandResponse(response, super.isExit());
    }
}
