package commands;

import java.io.IOException;
import core.Storage;
import core.TaskList;
import exceptions.DukeException;

/**
 * Handles user's commands concerning marking a task as 
 * done inside Core.Duke.
 */
public class DoneCommand extends Command {

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        validateDoneCommand(command);
        try {
            return taskList.markAsDone(Integer.parseInt(command[1]), storage);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The completed task's index must be a number.");
        }
    }

    private static void validateDoneCommand(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("OOPS!!! The completed task's index must be mentioned.");
        }
    }
}