package duke.ui;

/**
 * A class to handle all the user interface. This serves as an abstraction
 * for all the print statements.
 */
public class UI {

    /**
     * A wrapper method to send the message to the UI. This method is then used to pass the response to the dialogBOx.
     * 
     * @param message
     * @return
     */
    public String returnReponse(String message){
        return message;
    }

    public String returnWelcomeMessage(){
        return "Hello, I'm Duke! What can I do for you?";
    }

}