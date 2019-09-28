package duke.util;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;

/**
 * Deals with making sense of the user's input and command.
 */
public class Parser {
    /**
     * Parse and interprets the input from the user.
     *
     * @param input Input from the user.
     * @return Command representing what user's input.
     * @throws DukeException If input is invalid or not in the proper format.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            try {
                return new DoneCommand(inputArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the task number you wish to\n     mark as done!");
            }
        case "delete":
            try {
                return new DeleteCommand(inputArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the task number you wish to\n     delete!");
            }
        case "todo":
            try {
                return new AddCommand(new Todo(inputArr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        case "deadline":
            try {
                String[] detailsArr = inputArr[1].split(" /by ");
                return new AddCommand(new Deadline(detailsArr[0], detailsArr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(
                        "Please follow the format:\n     deadline <description> /by <DD/MM/YYYY HHMM>");
            }
        case "event":
            try {
                String[] detailsArr = inputArr[1].split(" /at ");
                return new AddCommand(new Event(detailsArr[0], detailsArr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(
                        "Please follow the format:\n     event <description> /at <DD/MM/YYYY HHMM>");
            }
        case "find":
            try {
                return new FindCommand(inputArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify what you are searching for!");
            }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }
}
