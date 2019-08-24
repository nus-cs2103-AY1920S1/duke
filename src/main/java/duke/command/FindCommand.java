package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.List;

public class FindCommand extends Command {

    private String[] itemsToFind;

    public FindCommand(String[] itemToFind) {
        this.itemsToFind = itemToFind;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> taskMatchedList = taskList.findTasks(itemsToFind);
        String formattedTasks = taskList.getTasksInString(taskMatchedList);
        ui.showMessage(Messages.FIND_TASK_MESSAGE, Messages.COMMAND_INDENTATION + formattedTasks);
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
