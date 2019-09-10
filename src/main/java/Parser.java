package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeMissingDescriptionException;
import seedu.duke.exception.DukeDeadlineMissingDateException;
import seedu.duke.exception.DukeNoSuchCommandException;
import seedu.duke.exception.DukeEventMissingDateException;
import seedu.duke.exception.DukeMissingIndexException;
import seedu.duke.exception.DukeMissingSearchTermException;
import seedu.duke.exception.DukeInvalidSortParameterException;
/**
 * Reads in the input line, and parses accordingly.
 * Will return null if trying to access information that doesn't exist for that command.
 * @author Lim Daekoon
 */

public class Parser {

    private static final String[] ALLOWED_SORT_BY = {"name", "date"};
    private static final String[] ALLOWED_SORT_ORDER = {"ascending", "descending"};

    private String type;
    private String description;
    private String time;
    private Integer index;
    private String searchTerm;
    private String sortBy;
    private String sortOrder;

    /**
     * Constructs a Parser object based on input string.
     * @param input The command String to be parsed.
     * @throws DukeException If there are problems with the command.
     */
    public Parser(String input) throws DukeException {

        this.description = null;
        this.time = null;
        this.index = null;
        this.searchTerm = null;
        this.sortBy = null;
        this.sortOrder = null;

        String[] inputs = input.split(" ", 2);
        String[] params;

        this.type = inputs[0];
        switch (type) {
        case "list":
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
            break;

        case "todo":
            if (inputs.length <= 1) {
                throw new DukeMissingDescriptionException();
            }
            this.description = inputs[1];
            break;

        case "deadline":
            if (inputs.length <= 1) {
                throw new DukeMissingDescriptionException();
            }
            params = inputs[1].split(" /by ", 2);
            if (params.length == 1) {
                throw new DukeDeadlineMissingDateException();
            }
            this.description = params[0];
            this.time = params[1];
            break;

        case "event":
            if (inputs.length <= 1) {
                throw new DukeMissingDescriptionException();
            }
            params = inputs[1].split(" /at ", 2);
            if (params.length == 1) {
                throw new DukeEventMissingDateException();
            }
            this.description = params[0];
            this.time = params[1];
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
            break;

        case "find":
            if (inputs.length <= 1) {
                throw new DukeMissingSearchTermException();
            }
            this.searchTerm = inputs[1];
            break;

        case "sort":
            if (inputs.length <= 1) {
                throw new DukeInvalidSortParameterException();
            }
            params = inputs[1].split(" ");
            if (params.length < 2) {
                throw new DukeInvalidSortParameterException();
            }

            validateSortBy(params[0]);
            validateSortOrder(params[1]);

            this.sortBy = params[0];
            this.sortOrder = params[1];
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

    /**
     * Returns the time included in the command.
     * @return Time included in the command, if any. Otherwise, returns null.
     */

    public String getSearchTerm() {
        return this.searchTerm;
    }

    /**
     * Returns the sort type included in the command.
     * @return Sort type included in the command, if any. Otherwise, returns null.
     */

    public String getSortBy() {
        return this.sortBy;
    }

    /**
     * Returns the sort order included in the command.
     * @return Sort order included in the command, if any. Otherwise, returns null.
     */

    public String getSortOrder() {
        return this.sortOrder;
    }

    // Checks if the sort by parameter is allowed.
    // Throws an error if it isn't.
    private void validateSortBy(String sortBy) throws DukeInvalidSortParameterException {
        for (int z = 0; z < ALLOWED_SORT_BY.length; z++) {
            if (sortBy.equals(ALLOWED_SORT_BY[z])) {
                return;
            }
        }

        throw new DukeInvalidSortParameterException();
    }

    // Checks if the sort order parameter is allowed.
    // Throws an error if it isn't.
    private void validateSortOrder(String sortOrder) throws DukeInvalidSortParameterException {
        for (int z = 0; z < ALLOWED_SORT_ORDER.length; z++) {
            if (sortOrder.equals(ALLOWED_SORT_ORDER[z])) {
                return;
            }
        }

        throw new DukeInvalidSortParameterException();
    }
}
