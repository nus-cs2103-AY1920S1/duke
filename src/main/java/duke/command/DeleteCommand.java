package duke.command;

import duke.Ui;
import duke.data.DukeData;

import java.io.IOException;

public class DeleteCommand implements Command {
    private int taskIndex;

    /**
     * Creates a new Delete Command with the given taskIndex.
     * @param taskIndex the index number of the task to be deleted
     */
    public DeleteCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }
    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the output for the delete command
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        String deleted = ui.showDelete(this.taskIndex - 1, dukeData.load());
        dukeData.removeTask(this.taskIndex - 1);
        return deleted;
    }
}
