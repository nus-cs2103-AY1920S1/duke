package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents the actions to execute when the command 'todo' is triggered.
 */

public class AddToDoCommand extends Command {

    private Task task;

    /**
     * Returns an AddEventCommand object from the commandArray, an array of words
     * which make up the initial user input
     * 
     * @param commandArray Array of Strings that form the initial user input
     */
    public AddToDoCommand(String[] commandArray) {
        String taskLine = "";
        for (int i = 1; i < commandArray.length; i++) {
            taskLine += " " + commandArray[i];
        }
        this.task = new Task(taskLine, false);
    }

    /**
     * Adds a Task object into the TaskList as per the command inputted
     * 
     * @param tasks   List of Tasks
     * @param ui      User Interface displaying the tasks in the TaskList
     * @param storage External storage where the list of tasks is stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getSize());
        try {
            storage.writeToFile(task.toFile());
        } catch (IOException e) {
            ui.showIOException(e);
        }
    }
}
