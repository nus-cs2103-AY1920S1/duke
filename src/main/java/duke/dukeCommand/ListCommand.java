package duke.dukeCommand;

import duke.dukeHelper.Storage;
import duke.dukeHelper.Ui;
import duke.dukeTask.TaskList;

public class ListCommand extends Command {

    public ListCommand(String filePath, String[] inputSplit) {
        super(filePath, inputSplit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.toArrayList());
    }
}
