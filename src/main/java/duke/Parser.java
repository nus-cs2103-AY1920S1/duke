package duke;


import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDateTime;

/**
 * Parser for parsing user input commands.
 */
public class Parser {

    /**
     * Split a user input according to the keyword (e.g. "/by")
     *
     * @param input
     * @param keyword
     * @return An array of Strings, first comes before the keyword and the second comes after.
     * @throws DukeException
     */
    private static String[] splitByKeyword(String input, String keyword) throws DukeException {
        try {
            int index;
            String[] res = new String[2];
            index = input.indexOf(keyword);
            if (index == -1) {
                throw new DukeException("No keyword " + keyword + " is found.");
            }
            res[0] = input.substring(0, index - 1);
            if (res[0].length() == 0) {
                throw new DukeException("No description found before keyword " + keyword + ".");
            }
            res[1] = input.substring(index + keyword.length() + 1);
            if (res[1].length() == 0) {
                throw new DukeException("No description found after keyword " + keyword + ".");
            }
            return res;
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Please check your format around keyword " + keyword);
        }
    }

    /**
     * Parse user input and output a Command object.
     * @param userInput
     * @return A Command that can be executed
     * @throws DukeException
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.matches("bye")) {
            return new ExitCommand();
        } else if (userInput.matches("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("done")) {
            return new DoneCommand(Integer.parseInt(userInput.split(" ")[1]) - 1);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(userInput.split(" ")[1]) - 1);
        } else if (userInput.startsWith("todo")) {
            if (userInput.length() == 4) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String restOfInput = userInput.substring(5);
            return new AddCommand(new ToDo(restOfInput));
        } else if (userInput.startsWith("deadline")) {
            if (userInput.length() == 8) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            userInput = userInput.substring(9);
            String[] temp = splitByKeyword(userInput, "/by");
            return new AddCommand(new Deadline(temp[0], LocalDateTime.from(Duke.dateTimeFormatter.parse(temp[1]))));
        } else if (userInput.startsWith("event")) {
            if (userInput.length() == 5) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            userInput = userInput.substring(6);
            String[] temp = splitByKeyword(userInput, "/at");
            return new AddCommand(new Event(temp[0], LocalDateTime.from(Duke.dateTimeFormatter.parse(temp[1]))));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
