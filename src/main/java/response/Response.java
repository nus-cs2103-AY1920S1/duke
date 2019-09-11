package response;

import exception.DukeException;
import exception.IncompleteInputException;
import task.TaskList;

public abstract class Response {
    String input;
    String output;
    TaskList myStorage;

    public Response() {
    }

    public Response(String i, TaskList x) {
        input = i;
        myStorage = x;
    }

    public abstract void respondToInput() throws IncompleteInputException, DukeException;


    public String returnResponse() throws Exception {
        try {
            this.respondToInput();
            return output;
        } catch (Exception err) {
            throw err;
        }

        }

}
