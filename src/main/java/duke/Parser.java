/**
 * Represents a Parse Manager, which parses the inputs given by the user, so our other classes
 * are able to read it with ease.
 */
class Parser {
    /**
     * Returns a Command, and each command created is of a different class from the user's input.
     * 
     * <p>There are 2 types of commands, 1-word String command, and 2-or-more-word command.
     * E.g. of 1-word String: list, bye, help.
     * E.g. of 2-or-more-word String, todo, deadline, event, done, delete.
     * The user's input is split into 2 parts, and if it is only 1-word String, 
     * If the user inputs something that is not of the correct Input type, a DukeException is thrown.
     * 
     * @param input String inputted by the user to be parsed into a Command
     * @return A Command that is executed by DukeManager
     * @throws DukeException When the user Inputs something unreadable by the program.
     * @see Command#execute(Ui, TaskList, Storage)
     */
    public Command parseToCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        Action action = getAction(inputArr[0]);

        if (inputArr.length == 1) {
            switch (action) {
            case LIST :
                return new ListCommand();   
            case EXIT :
                return new ExitCommand();      
            case HELP :
                return new HelpCommand();      
            default :
                throwInputError(action);
                return null;
            }
        } else if (inputArr.length == 2) {
            switch (action) {
            case TODO :
                return new AddCommand(new Todo(inputArr[1]));
            case DEADLINE :
                return new AddCommand(createDeadline(inputArr[1]));
            case EVENT :
                return new AddCommand(createEvent(inputArr[1]));
            case DONE :
                return new DoneCommand(parseToNumber(inputArr[1], "Done"));
            case DELETE :
                return new DeleteCommand(parseToNumber(inputArr[1], "Delete"));
            case FIND :
                return new FindCommand(inputArr[1]);
            default :
                throw new DukeException("Oof. I apologize, but I do not understand.");
            }
        } else {
            // Not suppose to happen
            throw new DukeException("Oof. I apologize, but I do not understand.");
        }
    } 

    /**
     * Returns an Action enum from a single word String.
     * 
     * @param action The first word that is input by the user
     * @return An Action enum
     * @throws DukeException When the single word String is not of the stipulated cases
     */
    private Action getAction(String action) throws DukeException {
        // Fallthrough are made for the Capitalized versions of the String
        switch (action.toLowerCase()) {
        case "list" :
            return Action.LIST;
        case "bye" :
            return Action.EXIT;
        case "help" :
            return Action.HELP;
        case "todo" :
            return Action.TODO;
        case "deadline" :
            return Action.DEADLINE;
        case "event" :
            return Action.EVENT;
        case "done" :
            return Action.DONE;
        case "delete" :
            return Action.DELETE;
        case "find" :
            return Action.FIND;
        default :
            throw new DukeException("Oof. I apologize, but no such command exists.");
        }
    }
    
    /**
     * Returns the parsed task number given by the user used for some commands E.g. Done and Delete.
     * 
     * @param taskNumber The number given by the user that is parsed into the respective task.
     * @param action The first word that is input by the user.
     * @return An Integer that is, taskNumber that is pased.
     * @throws DukeException When there is no number behind the action.
     */
    private Integer parseToNumber(String taskNumber, String action) throws DukeException {
        try {
            return Integer.parseInt(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Oof. " + action + " requires a number behind.");
        }
    }

    /**
     * Returns a Task, or specifically, a Deadline.
     * 
     * <p>The taskArr string is split with ' /by ', which the taskArr[0] is the given
     * task, while taskArr[1] is the date, time or both.
     * Otherwise, it will throw a DukeException, if the format is wrong.
     * 
     * @param taskString A String that contain the task including the date to be split
     * @return A Deadline Task to be added.
     * @throws DukeException When the format of Deadline is wrong.
     * @see Deadline#Deadline(String, String)
     */
    private Task createDeadline(String taskString) throws DukeException {
        String[] taskArr = taskString.split(" /by ", 2);
        if (taskArr.length == 1) {
            throw new DukeException("Oof. There seems to be an error with your deadline format. "
                    + "Here's an example: \'deadline Handup Quiz /by 17/05/2019 14:05\'");
        } else {
            return new Deadline(taskArr[0], taskArr[1]);
        }
    }

    /**
     * Returns a Task, or specifically, an Event.
     * 
     * <p>The taskArr string is split with ' /by ', which the taskArr[0] is the given
     * task, while taskArr[1] is the date, time or both.
     * Otherwise, it will throw a DukeException, if the format is wrong.
     * 
     * @param taskString A String that contain the task including the date to be split.
     * @return An Event Task to be added.
     * @throws DukeException When the format of the Event is wrong.
     * @see Event#Event(String, String)
     */
    private Task createEvent(String taskString) throws DukeException {
        String[] taskArr = taskString.split(" /at ", 2);
        if (taskArr.length == 1) {
            throw new DukeException("Oof. There seems to be an eror with your event format" 
                    + "Here's an example: \'event Go to class /at 17/05/2019 14:05\'");
        } else {
            return new Event(taskArr[0], taskArr[1]);
        }
    }

    /**
     * Throws an Input Exception when a 2-or-more-Words String has only the word itself.
     * 
     * @param action The input Command/Action
     * @throws DukeException As the description of a 2-or-more-Words String is only 1-word.
     */
    private void throwInputError(Action action) throws DukeException {
        switch (action) {
        case TODO :
            throw new DukeException("Oof. The description of a todo cannot be empty.");
        case DEADLINE :
            throw new DukeException("Oof. The description of a deadline cannot be empty.");
        case EVENT :
            throw new DukeException("Oof. The description of a event cannot be empty."); 
        case DONE :
            throw new DukeException("Oof. The description of a done cannot be empty.");
        case DELETE :
            throw new DukeException("Oof. The description of a delete cannot be empty.");
        default :
            throw new DukeException("Oof. I apologize but I don't understand.");
        }
    }

}