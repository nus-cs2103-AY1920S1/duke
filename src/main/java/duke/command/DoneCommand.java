package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

/**
 * The DoneCommand handles any the marking of a task as 'done'.
 */
public class DoneCommand implements Command {
    private int taskIndex;

    /**
     * Constructs a DoneCommand object which executes when a task is marked done.
     * @param taskIndex the index of the task which will be marked done
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the Duke's response when a Task is marked done
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        dukeData.taskDone(this.taskIndex - 1); // array that is why -1 to index
        return ui.showDone(this.taskIndex, dukeData.load());
    }
}
