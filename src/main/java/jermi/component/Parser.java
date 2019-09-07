package jermi.component;

import jermi.command.AddCommand;
import jermi.command.ClearCommand;
import jermi.command.Command;
import jermi.command.DeleteCommand;
import jermi.command.DoneCommand;
import jermi.command.ExitCommand;
import jermi.command.FindCommand;
import jermi.command.ListCommand;
import jermi.exception.EmptyDescriptionException;
import jermi.exception.InvalidCommandException;
import jermi.exception.JermiException;
import jermi.type.TaskType;

/**
 * A class that deals with making sense of the user input.
 */
public class Parser {
    /** Command to list tasks. */
    private static final String LIST = "list";
    /** Command to exit Jermi. */
    private static final String EXIT = "bye";
    /** Command to clear list. */
    private static final String CLEAR = "clear";
    /** Command to add todo task. */
    private static final String TODO = "todo";
    /** Command to add deadline task. */
    private static final String DEADLINE = "deadline";
    /** Command to add event task. */
    private static final String EVENT = "event";
    /** Command to mark task as done. */
    private static final String DONE = "done";
    /** Command to delete a task. */
    private static final String DELETE = "delete";
    /** Command to find tasks. */
    private static final String FIND = "find";

    /**
     * Parses and returns the {@link Command} associated with the user input.
     *
     * @param inputCommand User input command.
     * @param inputDetails User input details.
     * @return Associated command.
     * @throws JermiException All checked exceptions in the Jermi program.
     */
    public Command parse(String inputCommand, String inputDetails) throws JermiException {
        Command command = null;
        switch (inputCommand) {
        case LIST:
            command = new ListCommand();
            break;
        case EXIT:
            command = new ExitCommand();
            break;
        case CLEAR:
            command = new ClearCommand();
            break;
        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            //Fallthrough
        case DONE:
            //Fallthrough
        case DELETE:
            //Fallthrough
        case FIND:
            if (inputDetails.isEmpty()) {
                throw new EmptyDescriptionException(inputCommand);
            }
            switch (inputCommand) {
            case TODO:
                command = new AddCommand(TaskType.TO_DO, inputDetails);
                break;
            case DEADLINE:
                command = new AddCommand(TaskType.DEADLINE, inputDetails);
                break;
            case EVENT:
                command = new AddCommand(TaskType.EVENT, inputDetails);
                break;
            case DONE:
                command = new DoneCommand(inputDetails);
                break;
            case DELETE:
                command = new DeleteCommand(inputDetails);
                break;
            case FIND:
                command = new FindCommand(inputDetails);
                break;
            default:
                assert command != null : "command cannot be null";
            }
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
