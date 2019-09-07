package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class PriorityCommand extends Command {

    private int index;
    private int taskPriority;

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
