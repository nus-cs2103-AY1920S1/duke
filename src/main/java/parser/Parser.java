package parser;

import command.*;
import exception.DukeException;

/**
 * Used to parse String user input/ text file to get corresponding Command object to execute.
 */
public class Parser {

    /**
     * Process user input/ line from txt file and gets appropriate command.
     * @param instruction String from user input/ text file
     * @return Corresponding Task to be executed in run method of Duke class.
     * @throws DukeException When there are no matching command. i.e user input or line from txt file is invalid.
     */
    public static Command parse(String instruction) throws DukeException {
        String[] wordArr = instruction.split(" ", 2);
        FullCommand command = FullCommand.getByName(wordArr[0]);
        try {
            switch (command) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case DONE:
                return new EditCommand(Integer.parseInt(wordArr[1]) - 1);
            case DELETE:
                return new DeleteCommand(Integer.parseInt(wordArr[1]) - 1);
            case FIND:
                return new FindCommand(wordArr[1]);
            case RECUR:
                return new RecurCommand(wordArr[1]);
            case REVERT:
                return new RevertCommand(Integer.parseInt(wordArr[1]) - 1);
            case CLEAR:
                return new ClearCommand();
            default:
                if (wordArr[1].equals("")) {
                    throw new DukeException("");
                }
                return new AddCommand(command, wordArr[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Give instructions in the format: (instruction type) (details)");
        } catch (NumberFormatException e) {
            throw new DukeException("'done' and 'delete' must be followed by an integer index.");
        }
    }
}
