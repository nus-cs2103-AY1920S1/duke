package response;

import exception.DukeException;
import exception.IncompleteInputException;
import task.TaskList;

/**
 * Abstract class to represent a response from the Duke program, to pass onto UI
 */

//Idea for Response derived from https://github.com/uberSaiyan/duke/blob/master/src/main/java/duke/util/Response.java
public abstract class Response {
    String input;
    String output;
    TaskList myList;

    public Response() {
    }

    public Response(String i, TaskList x) {
        input = i;
        myList = x;
    }

    /**
     * Creates a Command based on the parsed String input and execute it
     *
     * @throws IncompleteInputException  if input is an incomplete added task
     * @throws DukeException if input is not recognizable
     */

    public abstract void respondToInput() throws IncompleteInputException, DukeException;

    /**
     * Through respondToInput, create an output String from the invoked Command
     *
     * @return String of output
     */

    public String returnResponse() throws Exception {
        try {
            this.respondToInput();
            return output;
        } catch (Exception err) {
            throw err;
        }

        }

}
