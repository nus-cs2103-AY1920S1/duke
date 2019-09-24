package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

/**
 * The ListCommand handles the command where the user requests to view the tasks in his/her list.
 */
public class ListCommand implements Command {

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of the list of tasks added to the Duke Program
     */
    @Override
    public String execute(DukeData dukeData, Ui ui, TaskList taskList) {
        return ui.showList(taskList.getList());
    }
}
