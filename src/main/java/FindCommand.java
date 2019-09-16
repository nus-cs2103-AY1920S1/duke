import java.io.IOException;

public class FindCommand extends Command {

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        validateFindCommand(command);
        return taskList.printMatchingTasks(command[1]);
    }

    private static void validateFindCommand(String command[]) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Find query must be specified!!!");
        }
    }    
}