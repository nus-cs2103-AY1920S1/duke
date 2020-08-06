package jermi.component;

import static jermi.misc.Constant.CLEAR_COMMAND;
import static jermi.misc.Constant.DEADLINE_COMMAND;
import static jermi.misc.Constant.DELETE_COMMAND;
import static jermi.misc.Constant.DONE_COMMAND;
import static jermi.misc.Constant.EVENT_COMMAND;
import static jermi.misc.Constant.EXIT_COMMAND;
import static jermi.misc.Constant.FIND_COMMAND;
import static jermi.misc.Constant.HELP_COMMAND;
import static jermi.misc.Constant.LIST_COMMAND;
import static jermi.misc.Constant.TODO_COMMAND;

import jermi.command.AddCommand;
import jermi.command.ClearCommand;
import jermi.command.Command;
import jermi.command.DeleteCommand;
import jermi.command.DoneCommand;
import jermi.command.ExitCommand;
import jermi.command.FindCommand;
import jermi.command.HelpCommand;
import jermi.command.ListCommand;
import jermi.exception.EmptyDescriptionException;
import jermi.exception.InvalidCommandException;
import jermi.exception.JermiException;
import jermi.misc.TaskType;

/**
 * A class that deals with making sense of the user input.
 */
public class Parser {
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
        case LIST_COMMAND:
            command = new ListCommand();
            break;
        case EXIT_COMMAND:
            command = new ExitCommand();
            break;
        case CLEAR_COMMAND:
            command = new ClearCommand();
            break;
        case HELP_COMMAND:
            command = new HelpCommand();
            break;
        case TODO_COMMAND:
            //Fallthrough
        case DEADLINE_COMMAND:
            //Fallthrough
        case EVENT_COMMAND:
            //Fallthrough
        case DONE_COMMAND:
            //Fallthrough
        case DELETE_COMMAND:
            //Fallthrough
        case FIND_COMMAND:
            if (inputDetails.isEmpty()) {
                throw new EmptyDescriptionException(inputCommand);
            }
            switch (inputCommand) {
            case TODO_COMMAND:
                command = new AddCommand(TaskType.TO_DO, inputDetails);
                break;
            case DEADLINE_COMMAND:
                command = new AddCommand(TaskType.DEADLINE, inputDetails);
                break;
            case EVENT_COMMAND:
                command = new AddCommand(TaskType.EVENT, inputDetails);
                break;
            case DONE_COMMAND:
                command = new DoneCommand(inputDetails);
                break;
            case DELETE_COMMAND:
                command = new DeleteCommand(inputDetails);
                break;
            case FIND_COMMAND:
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
