package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * DoneCommand class which extends the abstract class Command.
 * This class handles the completion of tasks, and subsequent updating
 * the txt file.
 */
public class DoneCommand extends Command {
    
    protected String command;
    
    /**
     * Class constructor.
     *
     * @param command Duke.command to complete task
     */
    public DoneCommand(String command) {
        this.command = command;
    }
    
    /**
     * Method that overrides the abstract method in Command class.
     * Completes the task requested by the user from the Duke.TaskList by marking them as done.
     * Update the txt file by marking the respective task as done.
     *
     * @param tasks   ArrayList of Tasks that keep tracks of the Tasks.
     * @param storage Handles the reading and writing of the txt file.
     */
    public String execute(TaskList tasks, Storage storage) {
        storage.updateComplete(command);
        return tasks.completeTask(command);
    }
    
    /**
     * DoneCommand does not exit program.
     *
     * @return False as this Duke.command does not end the program.
     */
    public boolean isExit() {
        return false;
    }
}
