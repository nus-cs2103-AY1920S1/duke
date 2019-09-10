package Logic;

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

    public CommandParser(String delimiter){
        this.DELIMITER = delimiter;
    }

    private Command parseName(String commandName, String arguments){
        if(commandName.equals(EXIT)){
            return new ExitCommand();
        } else if (commandName.equals(LIST)){
            return new ListCommand();
        } else if (commandName.equals(DONE)){
            return new DoneCommand(arguments);
        } else if (commandName.equals(DELETE)){
            return new DeleteCommand(arguments);
        } else if (commandName.equals(TODO)){
            return new TodoCommand(arguments);
        } else if (commandName.equals(EVENT)){
            return new EventCommand(arguments);
        } else if (commandName.equals(DEADLINE)){
            return new DeadlineCommand(arguments);
        } else if (commandName.equals(FIND)){
            return new FindCommand(arguments);
        } else if (commandName.equals(HELP)) {
            return new HelpCommand();
        } else {
            return new WrongCommand();
        }
    }

    public Command parseCommand(String s){
        String[] sp = s.split(DELIMITER, 2);

        if(sp.length < 2){
            return parseName(sp[0], null);
        } else {
            return parseName(sp[0], sp[1]);
        }


    }
}
