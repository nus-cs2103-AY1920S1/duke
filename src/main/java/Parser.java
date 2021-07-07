/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Constructor for Parser class.
     */
    public Parser() {
    }

    /**
     * Constructor for Parser class. Converts string of user input into actual command to be executed.
     * @param input string of command given by user.
     * @return CommandType.
     */
    public CommandType parse(String input) {
        String[] commandSplit = input.split(" ");
        String taskType = commandSplit[0];
        switch (taskType) {
        case ("todo") :
            return CommandType.TODO;
        case ("event") :
            return CommandType.EVENT;
        case ("deadline") :
            return CommandType.DEADLINE;
        case ("list") :
            return CommandType.LIST;
        case ("delete") :
            return CommandType.DELETE;
        case ("done") :
            return CommandType.DONE;
        case ("bye") :
            return CommandType.BYE;
        case ("find") :
            return CommandType.FIND;
        case ("help") :
            return CommandType.HELP;
        default :
            return CommandType.ERROR;
        }
    }
}