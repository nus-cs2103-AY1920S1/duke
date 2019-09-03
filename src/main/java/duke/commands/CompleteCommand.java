package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/** Command invoked when the "done" command is encountered. */
public class CompleteCommand extends Command {
    int taskNum;

    /**
     * Constructor.
     *
     * @param taskNum the 1-indexed task number to be marked as complete
     */
    public CompleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }


    /**
     * Will mark the task associated with <code>taskNum</code> as complete and
     * save the current TaskList.
     *
     * @param ui       Ui object that is responsible for printing output as a response
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException
     */
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        //Mark task as complete
        allTasks.completeTask(this.taskNum);

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
    }
}
