package duke.command;

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
     * which make up the initial user input.
     * 
     * @param commandArray Array of Strings that form the initial user input
     */
    public AddToDoCommand(String[] commandArray) {
        StringBuilder taskNameBuilder = new StringBuilder();
        // creates a taskName string by appending all the words in the user input
        for (int i = 1; i < commandArray.length; i++) {
            taskNameBuilder.append(" " + commandArray[i]);
        }
        String taskName = taskNameBuilder.toString();
        this.task = new Task(taskName, false);
    }

    /**
     * Adds a Task object into the TaskList as per the command inputted.
     * 
     * @param tasks   List of Tasks
     * @param storage External storage where the list of tasks is stored
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        assert task != null : "task should hold an actual Task object.";
        try {
            storage.writeToFile(task.toFile());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }
}
