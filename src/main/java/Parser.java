import javax.swing.UIManager;

class Parser {
    public Parser() {}

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
                return new DoneCommand(inputArr[1]);
            case DELETE :
                return new DeleteCommand(inputArr[1]);
            default :
                uiManager.throwGeneralError();
                return null;
            }
        } else {
            uiManager.throwGeneralError();
            return null;
        }
    } 

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

}