package duke.help;

import duke.command.CommandType;
import duke.exception.IllegalCommandTypeException;

public class HelpInfoOfAddCommand extends HelpInformation {
    private CommandType.SubCommandType subCommandType;

    /**
     * Constructor specifying the sub command type of an Add command.
     * @param subCommandType the sub command type of an Add command.
     * @throws IllegalCommandTypeException If the sub command type is not Todo, Event or Deadline.
     */
    public HelpInfoOfAddCommand(CommandType.SubCommandType subCommandType) {
        switch (subCommandType) {
        case Todo:
        case Event:
        case Deadline:
            this.subCommandType = subCommandType;
            break;
        default:
        }
    }

    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        switch (subCommandType) {
        case Todo:
            return "The format of Todo command is:\ntodo <task description>";
        case Event:
            return "The format of Event command is:\nevent <task description> /at (DD/MM/YYYY) HH:MM\n"
                    + "If date is omitted, then it is set to be today by default";
        case Deadline:
            return "The format of Deadline command is:\ndeadline <task description> /by (DD/MM/YYYY) HH:MM\n"
                    + "If date is omitted, then it is set to be today by default";
        default:
            return "";
        }
    }
}
