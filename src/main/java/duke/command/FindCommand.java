package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;

import java.io.IOException;

/**
 * FindCommand class which extends the abstract class Command.
 * This class handles the finding of tasks, and subsequent updating
 * the txt file.
 */
public class FindCommand extends Command {
    
    public String command;
    
    /**
     * Class constructor.
     *
     * @param command Duke.command to complete task
     */
    public FindCommand(String command) {
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
    public String execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, InvalidInputException,
        InvalidDescriptionException, IOException {
        return tasks.findTask(this.command);
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
