package duke.help;

import duke.command.CommandType;
import duke.exception.IllegalCommandTypeException;

public class HelpInfoOfAddCommand extends HelpInformation {
    private CommandType.SubCommandType subCommandType;

    public HelpInfoOfAddCommand(CommandType.SubCommandType subCommandType) throws IllegalCommandTypeException {
        switch (subCommandType) {
        case Todo:
        case Event:
        case Deadline:
            break;
        default:
            throw new IllegalCommandTypeException(subCommandType + " is not a Add command");
        }
        this.subCommandType = subCommandType;
    }

    @Override
    public String getHelpInformation() {
        switch (subCommandType) {
        case Todo:
            return "The format of Todo command is:\ntodo <task description>";
        case Event:
            return "The format of Event command is:\nevent <task description> /at (DD/MM/YYYY) HH:MM\n" +
                    "If date is omitted, then it is set to be today by default";
        case Deadline:
            return "The format of Deadline command is:\ndeadline <task description> /by (DD/MM/YYYY) HH:MM\n" +
                    "If date is omitted, then it is set to be today by default";
        default:
            return "";
        }
    }
}
