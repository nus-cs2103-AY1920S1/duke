public class Ui {

    private static Parser commandInterpreter = new Parser();
    private static final String greetings = "Hello! I'm Duke \n" + "What can I do for you?";

    /**
     * Returns salutations after user type in something into text field in the JavaFx GUI.
     *
     */
    public String returnGreetings(){
        return greetings;
    }

    /**
     * Accepts a command as input.
     * @param input a command
     */
    public String takeInput(String input) {
        String message;
        try {
            //calls checkCommand to verify if command is valid
            message = commandInterpreter.checkCommand(input);
        } catch (NoValidCommandException ex) {
            return ex.returnErrorMessage();
        }
        return message;
    }

}
