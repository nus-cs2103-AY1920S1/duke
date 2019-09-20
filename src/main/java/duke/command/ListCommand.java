package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * ListCommand class which extends the abstract class Command.
 * This class handles the printing of all Duke.tasks in the Duke.TaskList.
 */
public class ListCommand extends Command {
    
    protected String command;
    
    /**
     * Class constructor.
     *
     * @param command Duke.command to show all the Duke.tasks in Duke.TaskList
     */
    public ListCommand(String command) {
        this.command = command;
    }
    
    /**
     * Method that overrides the abstract method in Command class.
     * Prints all the task from the Duke.TaskList.
     *
     * @param tasks   ArrayList of Tasks that keep tracks of the Tasks.
     * @param storage Handles the reading and writing of the txt file.
     */
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> tasksList = tasks.getTasks();
        String result = "Here are your tasks:\n";
        for (int i = 0; i < tasksList.size(); i++) {
            int j = i + 1;
            result += j + ": " + tasksList.get(i) + "\n";
        }
        if (tasksList.isEmpty()) {
            result = "You have no tasks right now!";
            return result;
        } else {
            return result;
        }
    }
    
    /**
     * ListCommand does not exit program.
     *
     * @return False as this Duke.command does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
