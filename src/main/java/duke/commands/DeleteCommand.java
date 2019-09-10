package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

/** Command invoked when the "delete" command is encountered. */
public class DeleteCommand extends Command {
    int taskNum;

    /**
     * Constructor.
     *
     * @param taskNum 1-indexed number associated with a given task
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task associated with <code>taskNum</code> and will save the
     * TaskList.
     *
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @return String representing Duke's response
     * @throws DukeException re-thrown from underlying method calls.
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        //Delete Task
        Task t = allTasks.deleteTask(this.taskNum);

        //UI Response
        ui.reset();
        ui.addSentence("Noted. I've removed this task:");
        ui.addSentence("\t" + t);
        ui.addSentence("Now you have " + allTasks.size() + " tasks in the list.");

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
        return ui.getResponse();
    }
}
