package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Deals with reading and making sense of the user input.
 */
public class Parser {

    /**
     * Reads user input, deciphers input and return an instance of corresponding command.
     *
     * @param input string from user input.
     * @return command to be executed.
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    public static Command parse(String input) throws DukeException {
        // Make sense of the user input
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.length() > 5 && input.substring(0, 4).equals("done")) {
            validateInput(input, 4);
            return new DoneCommand(Integer.parseInt(input.substring(5)));
        } else if (input.length() > 7 && input.substring(0, 6).equals("delete")) {
            validateInput(input, 6);
            return new DeleteCommand(Integer.parseInt(input.substring(7)));
        } else if (input.length() > 5 && input.substring(0, 4).equals("find")) {
            validateInput(input, 4);
            return new FindCommand(input.substring(5));
        } else if (input.length() > 5 && input.substring(0, 4).equals("sort")) {
            validateInput(input, 4);
            return new SortCommand(input.substring(5));
        } else {
            return new AddCommand(input);
        }
    }

    /**
     * Validates the format of user input.
     *
     * @param input string from user input.
     * @param min minimum length of user input.
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    private static void validateInput(String input, int min) throws DukeException {
        // Check if there is a white space following the command word
        if (input.charAt(min) != ' ') {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}