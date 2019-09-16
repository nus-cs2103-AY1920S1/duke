package duke;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.command.DoneCommand;
import duke.exception.DukeException;
import duke.exception.ExtraDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidNumberException;

/**
 * Parser class.
 */
class Parser {
    private static String[] oneLine;

    /**
     * check str is a number.
     *
     * @param str The str to be checked.
     * @return Ture if str is a number, otherwise False.
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * feature of find command.
     *
     * @return FindCommand object.
     */
    private FindCommand findFeature() throws DukeException {
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length == 2 && !oneLine[1].isBlank()) {
            return new FindCommand(oneLine);
        } else {
            throw new DukeException("error occur in findFeature in Parser");
        }
    }

    /**
     * feature of done command.
     *
     * @return DoneCommand object.
     */
    private DoneCommand doneFeature() throws DukeException {
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length == 2 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())) {
            return new DoneCommand(oneLine);
        } else if (oneLine.length == 2 && oneLine[1].trim().split(" ").length != 1) {
            throw new ExtraDescriptionException("There is extra description for done");
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
    }

    /**
     * feature of delete command.
     *
     * @return DeleteCommand object.
     */
    private DeleteCommand deleteFeature() throws DukeException {
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length != 1 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())) {
            return new DeleteCommand(oneLine);
        } else if (oneLine.length == 2 && oneLine[1].trim().split(" ").length != 1) {
            throw new ExtraDescriptionException("There is extra description for delete");
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
    }

    /**
     * feature of add command.
     *
     * @return AddCommand object.
     */
    private AddCommand childFeature() throws DukeException {
        String firstWord = oneLine[0];
        if (oneLine.length == 2 && !oneLine[1].isBlank()) {
            return new AddCommand(oneLine);
        } else {
            throw new DukeException("The description of a " + firstWord + " cannot be empty.");
        }
    }

    /**
     * parse the given input to be a command obj.
     *
     * @param input The input for parse.
     * @return The Command obj by the parse function.
     */
    Command parse(String input) throws DukeException {
        assert input != null : "input should be empty";

        Command outCommand = null;
        String cmd = input;
        oneLine = cmd.trim().split(" ", 2);
        String firstWord = oneLine[0];
        if (firstWord.equals("exit")) {
            if (oneLine.length == 2 && oneLine[1].trim().equals("duke")) {
                outCommand = new ExitCommand("exit duke");
            } else {
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } else if (firstWord.equals("bye")) {
            if (oneLine.length != 1) {
                throw new ExtraDescriptionException("There is extra description for bye");
            }
            outCommand = new ExitCommand("exit duke");
        } else if (firstWord.equals("list")) {
            if (oneLine.length != 1) {
                throw new ExtraDescriptionException("There is extra description for list");
            }
            outCommand = new ListCommand();
        } else if (firstWord.equals("find")) {
            outCommand = findFeature();
        } else if (firstWord.equals("done")) {
            outCommand = doneFeature();
        } else if (firstWord.equals("delete")) {
            outCommand = deleteFeature();
        } else if (firstWord.equals("todo") || firstWord.equals("deadline")
                || firstWord.equals("event")) {
            outCommand = childFeature();
        } else {
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
        assert outCommand != null : "outCommand from parse should not be empty";
        return outCommand;
    }
}
