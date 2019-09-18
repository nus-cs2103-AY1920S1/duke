package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Priority Command.
 */
public class PriorityCommand extends Command {

    private int index;
    private int taskPriority;

    /**
     * Constructor for PriorityCommand.
     * @param num is the taskNumber that needs to be changed.
     * @param taskPriority new taskPriority value.
     */
    public PriorityCommand(int num, int taskPriority) {
        super(false);
        this.index = num - 1;
        this.taskPriority = taskPriority;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.setPriority(index, taskPriority);

        storage.writeListToFile(taskList);
        return ui.showPriorityChange(taskList.getTask(index));
    }
}
