package jermi.component;

import static jermi.misc.Constant.PARSER_CLEAR;
import static jermi.misc.Constant.PARSER_DEADLINE;
import static jermi.misc.Constant.PARSER_DELETE;
import static jermi.misc.Constant.PARSER_DONE;
import static jermi.misc.Constant.PARSER_EVENT;
import static jermi.misc.Constant.PARSER_EXIT;
import static jermi.misc.Constant.PARSER_FIND;
import static jermi.misc.Constant.PARSER_LIST;
import static jermi.misc.Constant.PARSER_TODO;

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
        case PARSER_LIST:
            command = new ListCommand();
            break;
        case PARSER_EXIT:
            command = new ExitCommand();
            break;
        case PARSER_CLEAR:
            command = new ClearCommand();
            break;
        case PARSER_TODO:
            //Fallthrough
        case PARSER_DEADLINE:
            //Fallthrough
        case PARSER_EVENT:
            //Fallthrough
        case PARSER_DONE:
            //Fallthrough
        case PARSER_DELETE:
            //Fallthrough
        case PARSER_FIND:
            if (inputDetails.isEmpty()) {
                throw new EmptyDescriptionException(inputCommand);
            }
            switch (inputCommand) {
            case PARSER_TODO:
                command = new AddCommand(TaskType.TO_DO, inputDetails);
                break;
            case PARSER_DEADLINE:
                command = new AddCommand(TaskType.DEADLINE, inputDetails);
                break;
            case PARSER_EVENT:
                command = new AddCommand(TaskType.EVENT, inputDetails);
                break;
            case PARSER_DONE:
                command = new DoneCommand(inputDetails);
                break;
            case PARSER_DELETE:
                command = new DeleteCommand(inputDetails);
                break;
            case PARSER_FIND:
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
