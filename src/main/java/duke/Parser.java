package duke;

import java.util.Scanner;

import commands.*;
import exceptions.DukeException;

/**
 * Parser is a class that handles the parsing of user input.
 * It contains a method parse that instantiates and returns
 * the appropriate type of Command object associated with the user input.
 */
public class Parser {

    /**
     * Returns a Command object that is one of 6 types:
     * AddCommand, DeleteCommand, DoneCommand,
     * FindCommand, ExitCommand and ListCommand.
     * The type of Command object returned is based on the user input:
     * add [description] [datetime] (datetime only required for Event and Deadline Tasks),
     * delete [task number], done [task number], find [keyword],
     * exit, and list commands respectively.
     *
     * @param fullCommand the line of user input.
     * @return a Command object associated with user input.
     * @throws DukeException  If there is invalid input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Scanner sc = new Scanner(fullCommand);
        String command = sc.next();
        if (command.equals("bye")) {
            return new ExitCommand(fullCommand);
        } else if (command.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (command.startsWith("done")) {
            return new DoneCommand(fullCommand);
        } else if (command.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else if (command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event")) {
            return new AddCommand(fullCommand);
        } else {
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        }
    }

}
