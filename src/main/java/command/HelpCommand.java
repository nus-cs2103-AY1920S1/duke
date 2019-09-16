package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class HelpCommand extends Command {

    private static final String HELP_WELCOME = "Welcome to help! Here are the available commands:";
    private static final String LIST_MSG = "list - Shows you a list of all current tasks.";
    private static final String FIND_MSG = "find [description] - List items matching description.";
    private static final String EXIT_MSG = "bye - Exits the application.";
    private static final String DELETE_MSG = "delete [index] - Removes task specified at index.";
    private static final String DONE_MSG = "done [index] - Sets task specified at index as done.";
    private static final String TODO_MSG = "todo [description] - Add a todo task.";
    private static final String DEADLINE_MSG = "deadline [description] [/by] [date] - Add a deadline task.";
    private static final String EVENT_MSG = "event [description] [/at] [date] - Add an event task.";

    /**
     * Executes the given task and prints the respective output.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";

        // Store help statements as strings
        output += "\n " + HELP_WELCOME + "\n";
        output += "\n " + LIST_MSG;
        output += "\n " + FIND_MSG;
        output += "\n " + EXIT_MSG;
        output += "\n " + DELETE_MSG;
        output += "\n " + DONE_MSG;
        output += "\n " + TODO_MSG;
        output += "\n " + EVENT_MSG;
        output += "\n " + DEADLINE_MSG;

        return output;


    }
}
