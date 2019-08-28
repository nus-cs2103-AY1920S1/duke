package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command{

    public ListCommand(String command){
        super(command, new Task());
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ArrayList<String> listToPrint = list.printList();
        ui.showList(listToPrint);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
