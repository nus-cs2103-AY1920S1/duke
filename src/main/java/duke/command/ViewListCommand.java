package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public class ViewListCommand extends Command {
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) {

        System.out.println(taskList.toString());
        return true;
    }
}
