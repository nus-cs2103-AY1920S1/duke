package jermi.component;

import jermi.command.Command;
import jermi.command.AddCommand;
import jermi.command.ListCommand;
import jermi.command.DeleteCommand;
import jermi.command.DoneCommand;
import jermi.command.ExitCommand;
import jermi.exception.EmptyDescriptionException;
import jermi.exception.InvalidCommandException;
import jermi.exception.JermiException;
import jermi.type.TaskType;

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
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            //Fallthrough
        case "done":
            //Fallthrough
        case "delete":
            if (inputDetails.isEmpty()) {
                throw new EmptyDescriptionException(inputCommand);
            }
            switch (inputCommand) {
            case "todo":
                command = new AddCommand(TaskType.TO_DO, inputDetails);
                break;
            case "deadline":
                command = new AddCommand(TaskType.DEADLINE, inputDetails);
                break;
            case "event":
                command = new AddCommand(TaskType.EVENT, inputDetails);
                break;
            case "done":
                command = new DoneCommand(inputDetails);
                break;
            case "delete":
                command = new DeleteCommand(inputDetails);
                break;
            }
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
