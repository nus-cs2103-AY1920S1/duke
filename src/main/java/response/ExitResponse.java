package response;

import command.ByeCommand;



public class ExitResponse extends Response {

    public ExitResponse() {
    }


    public void respondToInput() {
        ByeCommand sayBye = new ByeCommand();
        output =  sayBye.executeCommand();
    }

    @Override
    public String returnResponse()  {
        this.respondToInput();
        return output;

    }


}
