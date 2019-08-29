package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/** Command invoked when the "done" command is encountered */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Constructor
     * @param taskNum 1-indexed number of task
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Will mark the task associated with <code>taskNum</code> as done
     * @param ui Ui object that is responsible for printing output as a response
     * @param storage Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException
     */
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        //Mark task as complete
        Task t = allTasks.completeTask(this.taskNum);

        //UI Response
        ui.printLine();
        ui.printSentence("Nice! I've marked this task as done: ");
        ui.printSentence("\t" + t);
        ui.printLine();

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
    }
}

