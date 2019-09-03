/**
 * Deals with making sense of user commands into Command classes
 */
public class Parser {
    /**
     * String array containing user input string
     */
    private String[] inputArr;

    /**
     * Constructor for splitting input into main command and command details
     * @param input original user input string
     */
    public Parser(String input) {
        this.inputArr = input.split(" ", 2);
    }

    /**
     * Returns string array of main command and command details
     * @return string array
     */
    public String[] getInputArr() {
        return inputArr;
    }

    /**
     * Returns main command
     * @return main user command
     */
    public String getCommand() {
        return inputArr[0];
    }

    /**
     * Returns command details as integer for deleting and marking tasks as complete
     * @return array index pointer for task in task list
     */
    public int getPointer() {
        return Integer.parseInt(inputArr[1]);
    }

    /**
     * Returns command details
     * @return command details
     */
    public String getDetail() {
        if(inputArr.length > 1) {
            return inputArr[1];
        } else {
            return "";
        }
    }

    /**
     * Parses user commands into relevant Command classes to execute relevant actions
     * @param command First keyword entered by user determining command type
     * @param commandDetails Following indicating keywords
     * @param INDENT Constant indentation from start of line (formatting)
     * @return Commands which execute relevant actions to follow user commands
     */
    public static Command parse(String command, String commandDetails, String INDENT) {
        if(command.equals("bye")) {
            return new ExitCommand(command, commandDetails, INDENT);
        } else if(command.equals("list")) {
            return new ListCommand(command, commandDetails, INDENT);
        } else if(command.equals("done")) {
            return new DoneCommand(command, commandDetails, INDENT);
        } else if(command.equals("delete")) {
            return new DeleteCommand(command, commandDetails, INDENT);
        } else {
            return new AddCommand(command, commandDetails, INDENT);
        }
    }
}
