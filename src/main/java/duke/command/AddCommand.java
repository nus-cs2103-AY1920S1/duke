package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.InvalidInputException;

/**
 * AddCommand class which extends the abstract class Command.
 * This class handles any addition of Duke.tasks into the Duke.TaskList class and
 * subsequently updating the txt file.
 */
public class AddCommand extends Command {
    
    protected String command;
    
    /**
     * Class constructor.
     *
     * @param command Duke.command to add task
     */
    public AddCommand(String command) {
        this.command = command;
    }
    
    /**
     * Method that overrides the abstract method in Command class.
     * Adds the Duke.tasks requested by the user into Duke.TaskList.
     * Update the txt file by adding the respective task.
     *
     * @param tasks   ArrayList of Tasks that keep tracks of the Tasks.
     * @param storage Handles the reading and writing of the txt file.
     */
    public String execute(TaskList tasks, Storage storage) {
        try {
            if (command.startsWith("todo")) {
                storage.storeTodo(command);
                return tasks.addTodo(command);
            } else if (command.startsWith("deadline")) {
                storage.storeDeadline(command);
                return tasks.addDeadline(command);
            } else if (command.startsWith("event")) {
                storage.storeEvent(command);
                return tasks.addEvent(command);
            } else if (command.contains("doAfter")) {
                storage.storeEvent(command);
                return tasks.addDoAfter(command);
            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            return handleException(e);
        }
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
