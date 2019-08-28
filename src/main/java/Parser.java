/**
 * The Parser class is used to decode user input. The parser class then retrieves the appropriate
 * command.
 */
public class Parser {

    /**
     * Input by user.
     */
    String input;

    /**
     * Class constructor assigning user input to the object.
     * @param input The input by the user.
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * This method is used to retrieve the command from the user input.
     * @return String Command after processing user input
     */
    public String getCommand() {
        String[] inputStringArr = input.split(" ");
        String command = inputStringArr[0];
        return command;
    }
}
