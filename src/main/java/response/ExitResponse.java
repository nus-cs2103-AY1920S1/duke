package response;

import command.ByeCommand;

/**
 * Represents a response which encapsulates the bye response
 */

public class ExitResponse extends Response {

    /**
     * Creates a Bye command and instructs it to execute
     *
     */


    public void respondToInput() {
        ByeCommand sayBye = new ByeCommand();
        output =  sayBye.executeCommand();
    }

    /**
     * Through respondToInput, create an output String from the invoked ByeCommand
     *
     * @return String of output
     */

    @Override
    public String returnResponse()  {
        this.respondToInput();
        return output;

    }


}
