package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

import java.io.IOException;

/**
 * The DoneCommand handles any the marking of a task as 'done'.
 */
public class DoneCommand implements Command {
    private int taskIndex;

    /**
     * Constructs a DoneCommand object which executes
     * when a task is marked done.
     * @param taskIndex the index of the task which will be marked done
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of the Duke's response
     *         when a Task is marked done
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute(DukeData dukeData, Ui ui, TaskList taskList)
            throws IOException {
        taskList.markTaskAsDone(this.taskIndex);
        dukeData.update(taskList);
        return ui.showDone(this.taskIndex, taskList);
    }
}
