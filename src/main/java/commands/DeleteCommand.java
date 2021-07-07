package commands;

import java.io.IOException;
import core.Storage;
import core.TaskList;
import exceptions.DukeException;

/**
 * Handles user's commands concerning the deletion of a 
 * task in Core.Duke.
 */
public class DeleteCommand extends Command {

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        validateDeleteCommand(command);
        try {
            return taskList.deleteTask(Integer.parseInt(command[1]), storage);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The index of task to be deleted must be a number.");
        }
    }
    
    private static void validateDeleteCommand(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("OOPS!!! The index of task to be deleted must be mentioned.");
        }
    }

}