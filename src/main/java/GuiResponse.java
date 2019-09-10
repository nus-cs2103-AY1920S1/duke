import java.io.IOException;
import java.util.ArrayList;

class GuiResponse {

    private static Ui ui = new Ui();
    private static ArrayList<Task> taskList = new ArrayList<>(100);
    private static InputParser inputParser = new InputParser(taskList);
    private boolean isYesNoResponse = false;

    GuiResponse(){}

    public void setIsYesNoResponse(boolean yesNoResponse) {
        isYesNoResponse = yesNoResponse;
    }

    public boolean getIsYesNoResponse(){
        return isYesNoResponse;
    }

    String getResponse(String input) throws IOException {
        if (!input.equals("bye")) {
            inputParser.determineAction(input);
            String response = ui.getGuidedUserInterfaceMsg();
            ui.setGuidedUserInterfaceMsg("");
            return response;
        }
        return ui.goodByeMsg;
    }

    String getIsYesNoResponse(String input) throws IOException{
        if (input.equals("y")){
            setIsYesNoResponse(false);
        }
        else if (input.equals("n")){
            setIsYesNoResponse(false);
        }
        return ("Please choose 'y' or 'n'");
    }
}
