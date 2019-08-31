package duke.command;

import duke.task.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui) {
        taskList.printList();
    }

    public boolean isExit() {
        return false;
    }
}
