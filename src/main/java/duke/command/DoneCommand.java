package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    int index;

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        index = Integer.parseInt(ui.getRemainingWords().trim());
        tasks.getTaskArrayList().get(index - 1).markAsDone();
        storage.writeData();
        String toPrint = "Nice! I've marked this task as done: \n";
        toPrint += (tasks.getTaskArrayList().get(index - 1));
        return toPrint;
    }


}
