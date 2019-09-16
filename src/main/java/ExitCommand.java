import java.io.IOException;

/**
 * Handles the user command when the user decides to 
 * exit Duke.
 */
public class ExitCommand extends Command {

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        return new StringBuilder(showGoodbyeMessage());
    }

    private static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}