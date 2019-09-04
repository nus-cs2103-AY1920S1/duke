package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

/** Command invoked when the "done" command is encountered. */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Constructor.
     *
     * @param taskNum 1-indexed number of task
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Will mark the task associated with <code>taskNum</code> as done.
     *
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException re-thrown from underlying method calls.
     * @return String representing Duke's response
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        //Mark task as complete
        Task t = allTasks.completeTask(this.taskNum);

        //UI Response
        ui.reset();
        ui.addSentence("Nice! I've marked this task as done: ");
        ui.addSentence("\t" + t);

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
        return ui.getResponse();
    }
}

