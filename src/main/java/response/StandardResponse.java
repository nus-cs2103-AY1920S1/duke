package response;

import command.Command;
import driver.Parser;
import exception.DukeException;
import exception.IncompleteInputException;
import task.TaskList;

/**
 * Represents a response which encapsulates all "standard" responses,
 * where standard responses refer to everything but errors and exits
 *
 */

public class StandardResponse extends Response {

    public StandardResponse(String i, TaskList x) {
        super(i, x);
    }

    /**
     * Creates a Command based on the parsed String input and executes it
     *
     * @throws IncompleteInputException  if input is an incomplete added task
     * @throws DukeException if input is not recognizable
     */

    @Override
    public void respondToInput() throws IncompleteInputException, DukeException {
        try {
            Command curr = Parser.parse(input);
            output = curr.executeCommand(myList);
        } catch (IncompleteInputException err) {
            throw err;
        } catch (DukeException otherErr) {
            throw otherErr;
        }

    }
}


