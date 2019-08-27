package duke.command;


import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showTaskList(taskList);
    }
}
