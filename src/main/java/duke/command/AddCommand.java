package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;
import duke.task.Task;

import java.io.IOException;

/**
 * The AddCommand handles the adding of any ToDo, Deadline, or Event Tasks into the Duke program.
 */
public class AddCommand implements Command {
    private Task taskToBeAdded;

    /**
     * Creates a new Command which adds a new task into the Duke Program.
     * @param task the task object to be added
     */
    public AddCommand(Task task) {
        this.taskToBeAdded = task;
    }

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @return a string representation of the output for the add command
     */
    @Override
    public String execute(DukeData dukeData, Ui ui) throws IOException {
        TaskList tasks = dukeData.load();
        int index = tasks.getSize() + 1; // since index is always -1 of size of arraylist
        dukeData.addTask(index, this.taskToBeAdded);
        return ui.showTaskAdded(this.taskToBeAdded, tasks);
    }
}
