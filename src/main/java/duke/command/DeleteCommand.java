package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * DeleteCommand class which extends the abstract class Command.
 * This class handles the deletion of tasks, and subsequent updating
 * the txt file.
 */
public class DeleteCommand extends Command {
    
    protected String command;
    
    /**
     * Class constructor.
     *
     * @param command Duke.command to delete task
     */
    public DeleteCommand(String command) {
        this.command = command;
    }
    
    /**
     * Method that overrides the abstract method in Command class.
     * Deletes the task requested by the user from the Duke.TaskList
     * Update the txt file by removing the respective task.
     *
     * @param tasks   ArrayList of Tasks that keep tracks of the Tasks.
     * @param storage Handles the reading and writing of the txt file.
     */
    public String execute(TaskList tasks, Storage storage) {
        storage.updateDelete(command);
        return tasks.deleteTask(command);
    }
    
    /**
     * AddCommand does not exit program.
     *
     * @return False as this Duke.command does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
