package dukeCommand;

import dukeHelper.Storage;
import dukeHelper.Ui;
import dukeTask.TaskList;

public class ListCommand extends Command {

    public ListCommand(String filePath, String[] inputSplit) {
        super(filePath, inputSplit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.toArrayList());
    }
}
