package response;

import command.ErrorCommand;

public class ErrorResponse extends Response {
    Exception myError;


    public ErrorResponse(Exception x) {
        myError = x;
    }

    @Override
    public void respondToInput() {
        ErrorCommand myHandler = new ErrorCommand(myError);
        output = myHandler.executeCommand();
    }


        @Override
        public String returnResponse()  {
                this.respondToInput();
                return output;

        }
    }

