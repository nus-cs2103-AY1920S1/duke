package response;

import command.ErrorCommand;

/**
 * Represents a response which encapsulates all error responses
 */

public class ErrorResponse extends Response {
    Exception myError;



    public ErrorResponse(Exception x) {
        myError = x;
    }

    /**
     * Creates an error command based on the Exception (myError) and instructs it to execute
     *
     */

    @Override
    public void respondToInput() {
        ErrorCommand myHandler = new ErrorCommand(myError);
        output = myHandler.executeCommand();
    }

    /**
     * Through respondToInput, create an output String from the invoked ErrorCommand
     *
     * @return String of output
     */

        @Override
        public String returnResponse()  {
                this.respondToInput();
                return output;

        }
    }

