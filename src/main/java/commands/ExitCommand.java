package commands;

import core.Storage;
import core.TaskList;

/**
 * Handles the user command when the user decides to 
 * exit Core.Duke.
 */
public class ExitCommand extends Command {

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) {
        return new StringBuilder(showGoodbyeMessage());
    }

    private static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}