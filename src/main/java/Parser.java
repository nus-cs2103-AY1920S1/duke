package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeMissingDescriptionException;
import seedu.duke.exception.DukeDeadlineMissingDateException;
import seedu.duke.exception.DukeNoSuchCommandException;
import seedu.duke.exception.DukeEventMissingDateException;
import seedu.duke.exception.DukeMissingIndexException;
import seedu.duke.exception.DukeMissingSearchTermException;

/**
 * Reads in the input line, and parses accordingly.
 * Will return null if trying to access information that doesn't exist for that command.
 * @author Lim Daekoon
 */

public class Parser {

    private String type;
    private String description;
    private String time;
    private Integer index;
    private String searchTerm;

    /**
     * Constructs a Parser object based on input string.
     * @param input The command String to be parsed.
     * @throws DukeException If there are problems with the command.
     */
    public Parser(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);
        String[] params;

        this.type = inputs[0];
        switch (type) {
        case "bye":
            this.description = null;
            this.time = null;
            this.index = null;
            this.searchTerm = null;
            break;

        case "list":
            this.description = null;
            this.time = null;
            this.index = null;
            this.searchTerm = null;
            break;

        case "done":
            if (inputs.length <= 1) {
                throw new DukeMissingIndexException();
            }
            try {
                this.index = Integer.parseInt(inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printBlock("Please input a number");
            } catch (NumberFormatException e) {
                Ui.printBlock("Please input a number");
            }
            this.description = null;
            this.time = null;
            this.searchTerm = null;
            break;

        case "todo":
            this.index = null;

            if (inputs.length <= 1) {
                throw new DukeMissingDescriptionException();
            }
            this.description = inputs[1];
            this.time = null;
            this.searchTerm = null;
            break;

        case "deadline":
            this.index = null;

            if (inputs.length <= 1) {
                throw new DukeMissingDescriptionException();
            }
            params = inputs[1].split(" /by ", 2);
            if (params.length == 1) {
                throw new DukeDeadlineMissingDateException();
            }
            this.description = params[0];
            this.time = params[1];
            this.searchTerm = null;
            break;

        case "event":
            this.index = null;

            if (inputs.length <= 1) {
                throw new DukeMissingDescriptionException();
            }
            params = inputs[1].split(" /at ", 2);
            if (params.length == 1) {
                throw new DukeEventMissingDateException();
            }
            this.description = params[0];
            this.time = params[1];
            this.searchTerm = null;
            break;

        case "delete":
            if (inputs.length <= 1) {
                throw new DukeMissingIndexException();
            }
            try {
                this.index = Integer.parseInt(inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printBlock("Please input a number");
            } catch (NumberFormatException e) {
                Ui.printBlock("Please input a number");
            }
            this.description = null;
            this.time = null;
            this.searchTerm = null;
            break;

        case "find":
            this.index = null;

            if (inputs.length <= 1) {
                throw new DukeMissingSearchTermException();
            }
            this.searchTerm = inputs[1];
            this.description = null;
            this.time = null;
            break;

        default:
            throw new DukeNoSuchCommandException();
        }
    }

    /**
     * Returns the type of the command.
     * @return The type of the command, in String.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the index included in the command, if there is any.
     * @return Index included in the command, if any. Otherwise, returns null.
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * Returns the description included in the command.
     * @return Description included in the command, if any. Otherwise, returns null.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the time included in the command.
     * @return Time included in the command, if any. Otherwise, returns null.
     */
    public String getTime() {
        return this.time;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }
}
