package logic;

public class CommandParser {
    private final String DELIMITER;

    private final String TODO = "todo";
    private final String EVENT = "event";
    private final String DEADLINE = "deadline";
    private final String EXIT = "bye";
    private final String LIST = "list";
    private final String DONE = "done";
    private final String DELETE = "delete";
    private final String FIND = "find";
    private final String HELP = "help";

    /**
     * Creates an instance of CommandParser with a specified delimiter
     *
     * @param delimiter a string at which command arguments are separated by
     */
    public CommandParser(String delimiter) {
        this.DELIMITER = delimiter;
    }

    /**
     * Parses the command string and outputs the resulting command
     *
     * @param s String to be pared
     * @return resulting Command
     */
    public Command parseCommand(String s) {
        try{
            String[] sp = s.split(DELIMITER, 2);
            return parseName(sp[0], sp[1]);

        } catch(Exception E){
            return parseName(s, null);
        }

    }

    /**
     * Parses the command type and its arguments and outputs the corresponding Command
     *
     * @param commandType type of the command as a String
     * @param arguments   arguments of that command
     * @return the resulting Command
     */
    private Command parseName(String commandType, String arguments) {
        assert commandType != null: "commandType should not be null";

        if (commandType.equals(EXIT)) {
            return new ExitCommand();
        } else if (commandType.equals(LIST)) {
            return new ListCommand();
        } else if (commandType.equals(DONE)) {
            return new DoneCommand(arguments);
        } else if (commandType.equals(DELETE)) {
            return new DeleteCommand(arguments);
        } else if (commandType.equals(TODO)) {
            return new TodoCommand(arguments);
        } else if (commandType.equals(EVENT)) {
            return new EventCommand(arguments);
        } else if (commandType.equals(DEADLINE)) {
            return new DeadlineCommand(arguments);
        } else if (commandType.equals(FIND)) {
            return new FindCommand(arguments);
        } else if (commandType.equals(HELP)) {
            return new HelpCommand();
        } else {
            return new WrongCommand();
        }
    }
}
