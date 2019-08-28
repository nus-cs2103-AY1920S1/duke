package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.getTaskArrayList().size(); i++) {
            System.out.println(i + 1 + "." + tasks.getTaskArrayList().get(i));
        }
        ui.showLine();
    }

    /**
     * Returns true or false regarding whether this method will end the while loop in the duke method run()
     * @return false or true
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
