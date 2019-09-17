package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.RemindCommand;
import exception.DukeException;
import ui.Ui;

import java.util.Scanner;
public class Parser {


    private Scanner scanner = new Scanner(System.in);
    private static Ui ui = new Ui();

    public Parser() {
    }

    public static Command parse(String input) throws DukeException {
        Parser parser = new Parser();
        String action = parser.parseAction(input);

        switch (action) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "done":
            return new DoneCommand(input);

        case "delete":
            return new DeleteCommand(input);

        case "find":
            String keyword = parser.parseDescription("find", input);
            return new FindCommand(keyword);


        case "remind":
            return new RemindCommand();
        case "todo":
        case "deadline":
        case "event":
            String description = parser.parseDescription(action, input);
            return new AddCommand(input, action, description);

        default:
            throw new DukeException(ui.invalidCommand());
        }
    }

    /**
     * Parse the input to return the user action as a String.
     * Ensure that the input has a valid action.
     * If invalid, throw DukeException, indicate action is not recognised.
     * If action is valid but description is blank, ask for description.
     *
     * @param input Entire input command from the user
     * @return action Return the action command of the user
     * @throws DukeException If action or input is invalid
     */
    public String parseAction(String input) throws DukeException {
        String[] substrings = input.split(" ");
        String action = substrings[0];

        if (substrings.length == 0) { // Invalid command
            throw new DukeException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }


        return action;
    }

    /**
     * Parse the description from the input as a String.
     * For task.Event or task.Deadline Tasks, important to split substrings into
     * description and date/time, while for Todo Tasks and "find" command not required.
     *
     * @param input  The entire user input with action and task full description
     * @param action The designated user action,
     * @return description/substring The description of the task, without action and dateTime
     */
    public String parseDescription(String action, String input) throws DukeException {
        String[] substrings = input.split(" ");
        if (substrings.length == 1) { // No description
            throw new DukeException("    " + "\u2639" + " OOPS!!! The description of a " + action + " cannot be empty.");
        }
        String description = input.replace(action, "");

        //Split task and date or time
        String[] parts = description.split("/..");
        if (action.equals("todo") || action.equals("find")) {
            return description.trim(); //no date or time
        } else {
            description = parts[0].trim(); // Remove blank spaces
        }
        return description;
    }

    /**
     * Parse the date or time as a String.
     *
     * @param input  The entire user input with action and task full description
     * @param action The designated user action,
     * @return dateTime The date or time as a String
     */
    public String parseDateTime(String action, String input) throws DukeException {
        String substring = input.replace(action, "");
        //Split task and date time
        String[] parts = substring.split("/..");
        if (parts.length <= 1) {
            throw new DukeException(ui.invalidTask());
        }
        String dateTime = parts[1].trim(); // Remove blank spaces

        return dateTime;
    }


}
