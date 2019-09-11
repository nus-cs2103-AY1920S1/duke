package response;

import command.Command;
import driver.Parser;
import exception.DukeException;
import exception.IncompleteInputException;
import task.TaskList;

public class StandardResponse extends Response {

    public StandardResponse(String i, TaskList x) {
        super(i, x);
    }

    @Override
    public void respondToInput() throws IncompleteInputException, DukeException {
        try {
            Command curr = Parser.parse(input);
            output = curr.executeCommand(myStorage);
        } catch (IncompleteInputException err) {
            throw err;
        } catch (DukeException otherErr) {
            throw otherErr;
        }

    }
}


