/**
 * Represents a Parse Manager, which parses the inputs given by the user, so our other classes
 * are able to read it with ease.
 */
class Parser {
    /**
     * Default Constructor
     */
    public Parser() {}

    /**
     * Returns a Command, and each command created is of a different class from the user's input.
     * There are 2 types of commands, 1-word String command, and 2-or-more-word command.
     * E.g. of 1-word String: list, bye, help
     * E.g. of 2-or-more-word String, todo, deadline, event, done, delete
     * The user's input is split into 2 parts, and if it is only 1-word String, 
     * If the user inputs something that is not of the correct Input type, a DukeException is thrown
     * 
     * @param input String inputted by the user to be parsed into a Command
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @throws DukeException When the user Inputs something unreadable by the program.
     * @return A Command that is executed by DukeManager
     * @see {@link Command#execute(Ui, TaskList, Storage)}
     */
    public Command parseToCommand(String input, Ui uiManager) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        Action action = getAction(inputArr[0], uiManager);

        if (inputArr.length == 1) {
            switch (action) {
            case LIST :
                return new ListCommand();   
            case EXIT :
                return new ExitCommand();      
            case HELP :
                return new HelpCommand();      
            default :
                uiManager.throwInputError(action);
                return null;
            }
        } else if (inputArr.length == 2) {
            switch (action) {
            case TODO :
            case DEADLINE :
            case EVENT :
                return new AddCommand(action, inputArr[1]);
            case DONE :
                return new DoneCommand(parseToNumber(inputArr[1], "Done", uiManager));
            case DELETE :
                return new DeleteCommand(parseToNumber(inputArr[1], "Delete", uiManager));
            default :
                uiManager.throwGeneralError();
                return null;
            }
        } else {
            uiManager.throwGeneralError();
            return null;
        }
    } 

    /**
     * Returns an Action enum from a single word String
     * @param action The first word that is input by the user
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @throws DukeException When the single word String is not of the stipulated cases
     * @return An Action enum
     */
    private Action getAction(String action, Ui uiManager) throws DukeException {
        switch (action) {
        case "List" :
        case "list" :
            return Action.LIST;
        case "Bye" :
        case "bye" :
            return Action.EXIT;
        case "Help" :
        case "help" :
            return Action.HELP;
        case "Todo" :
        case "todo" :
            return Action.TODO;
        case "Deadline" :
        case "deadline" :
            return Action.DEADLINE;
        case "Event" :
        case "event" :
            return Action.EVENT;
        case "Done" :
        case "done" :
            return Action.DONE;
        case "Delete" :
        case "delete" :
            return Action.DELETE;
        default :
            uiManager.throwApologyError();
            return null;
        }
    }

    /**
     * Returns the parsed task number given by the user 
     * used for certain commands E.g. Done and Delete
     * 
     * @param taskNumber The 
     * @param action The first word that is input by the user
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @return An Integer that is, taskNumber that is pased
     * @throws DukeException When the action
     */
    private Integer parseToNumber(
            String taskNumber, String action, Ui uiManager) throws DukeException {
        Integer taskNo = null;
        try {
            taskNo = Integer.parseInt(taskNumber);
            return taskNo;
        } catch (Exception e) {
            uiManager.throwMissingNumberError(action);
            return null;
        }
    }

}