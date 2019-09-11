package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class HelpCommand extends Command {

    final String HELP_WELCOME = "Welcome to help! Here are the available commands:";
    final String LIST_MSG = "list - Shows you a list of all current tasks.";
    final String FIND_MSG = "find [description] - List items matching the description.";
    final String EXIT_MSG = "bye - Exits the application.";
    final String DELETE_MSG = "delete [index] - Removes task specified at index.";
    final String DONE_MSG = "done [index] - Sets task specified at index as done.";
    final String TODO_MSG = "todo [description] - Adds a todo task.";
    final String DEADLINE_MSG = "deadline [description] [/by] [date] - Adds a deadline task.";
    final String EVENT_MSG = "event [description] [/at] [date] - Adds an event task.";

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";

        // Store help statements as strings
        output += ui.getTopBorder();
        output += "\n  " + HELP_WELCOME + "\n";
        output += "\n  " + LIST_MSG;
        output += "\n  " + FIND_MSG;
        output += "\n  " + EXIT_MSG;
        output += "\n  " + DELETE_MSG;
        output += "\n  " + DONE_MSG;
        output += "\n  " + TODO_MSG;
        output += "\n  " + EVENT_MSG;
        output += "\n  " + DEADLINE_MSG;
        output += ui.getBottomBorder();

        return output;


    }
}
