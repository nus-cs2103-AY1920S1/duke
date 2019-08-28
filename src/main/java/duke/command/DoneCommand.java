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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        index = Integer.parseInt(ui.getRemainingWords().trim());
        tasks.getTaskArrayList().get(index - 1).markAsDone();
        storage.writeData();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTaskArrayList().get(index - 1));
        ui.showLine();
    }

    /**
     * Returns true or false regarding whether this method will end the while loop in the duke method run()
     * @return false or true
     */
    public boolean isExit() {
        return false;
    }
}
