package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Task;

/** Command invoked when the "delete" command is encountered */
public class DeleteCommand extends Command {
    int taskNum;

    /**
     * Constructor
     * @param taskNum 1-indexed number associated with a given task
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task associated with <code>taskNum</code> and will save the
     * TaskList.
     * @param ui Ui object that is responsible for printing output as a response
     * @param storage Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException
     */
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        //Delete Task
        Task t = allTasks.deleteTask(this.taskNum);

        //UI Response
        ui.printLine();
        ui.printSentence("Noted. I've removed this task:");
        ui.printSentence("\t" + t);
        ui.printSentence("Now you have " + allTasks.size() + " tasks in the list.");
        ui.printLine();

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
    }
}
