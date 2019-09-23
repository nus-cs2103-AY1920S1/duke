package execution;

import exception.DukeException;
import execution.commands.*;

/**
 * Represents a parser that parses a string value to decide which command to create based on the input string.
 */
public class Parser {

    /**
     * Parses the input string by breaking up the input string into a string array to seperate the action word and
     * description of task created.
     *
     * @param line is the input string
     * @return a Command object based on the first word of input key.
     * @throws DukeException is thrown when parser does not understand the first word of input key.
     */
    public static Command parse(String line) throws DukeException {

        String[] split = line.split(" ");
        String restOfInput = getRestOfInput(split);

        return parseForCommand(split[0], restOfInput);

    }

    /**
     * Parses the input string - action and rest , into a Command object.
     *
     * @param action string decides which type of Command object to be created.
     * @param rest string is the description of the task which be used in the constructor of a command.
     * @return a Command object.
     * @throws DukeException is thrown when parse does not understand the first word of input key.
     */
    private static Command parseForCommand(String action, String rest) throws DukeException {

        switch (action) {

            case "bye":
                return new ByeCommand(rest);

            case "list":
                return new ListCommand(rest);

            case "done":
                return new DoneCommand(rest);

            case "todo":
                return new TodoCommand(rest);

            case "deadline":
                return new DeadlineCommand(rest);

            case "event":
                return new EventCommand(rest);

            case "delete":
                return new DeleteCommand(rest);

            case "find":
                return new FindCommand(rest);

            case "deleteAll":
                return new MassDeleteCommand(rest);

            default:
                System.out.println("ERROR: " + rest);
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

    }

    /**
     * Removes the first word of the input string which is the action word. The resulting string is the description of
     * the task.
     *
     * @param split is the String array of the input string.
     * @return the string without the first word.
     */
    private static String getRestOfInput(String[] split) {

        String rest = "";

        for (int i = 1; i < split.length; i++) {
            rest += split[i];
            rest += " ";
        }

        return rest;

    }

}
