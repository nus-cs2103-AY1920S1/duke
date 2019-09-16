import java.io.IOException;

/**
 * Handles user's commands concerning the search of a 
 * particular task in Duke.
 */
public class FindCommand extends Command {

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        validateFindCommand(command);
        assert ! input.equals("") : "The input should have been caught as blank.";
        return taskList.printMatchingTasks(command[1]);
    }

    private static void validateFindCommand(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Find query must be specified!!!");
        }
    }    
}