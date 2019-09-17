package duke.help;

import duke.command.CommandType;

import java.util.Optional;

public class HelpInformation {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    public String getHelpInformation() {
        return "Welcome to Duke application. This is an application for you to easily create a personal task list "
                + "and retrieve tasks efficiently.\n"
                + "Duke supports Add(Todo, Event, Deadline), Done, Delete, List, Find, Exit command and filter."
                + "Type 'help <command name>' or 'help <filter name>' to learn more."
                + "Try 'help todo'!";
    }

    /**
     * Returns a help information class based on the command type.
     * @param commandType type of command that asks for help information.
     * @param subCommandType type of sub command that asks for help information.
     * @return a help information class based on the command type.
     */
    public static HelpInformation getHelpInformationFor(Optional<CommandType> commandType,
                                                        Optional<CommandType.SubCommandType> subCommandType) {
        if (commandType.isEmpty() && subCommandType.isEmpty()) {
            return getHelpInformationFor();
        } else if (commandType.isEmpty()) {
            return getHelpInformationFor(subCommandType.get());
        } else if (subCommandType.isEmpty()) {
            return getHelpInformationFor(commandType.get());
        } else {
            return getHelpInformationFor(commandType.get(), subCommandType.get());
        }
    }

    private static HelpInformation getHelpInformationFor(CommandType commandType) {
        switch (commandType) {
        case Delete:
            return new HelpInfoOfDeleteCommand();
        case Done:
            return new HelpInfoOfDoneCommand();
        case List:
            return new HelpInfoOfListCommand();
        case Exit:
            return new HelpInfoOfExitCommand();
        case Find:
            return new HelpInfoOfFindCommand();
        default:
            return new HelpInformation();
        }
    }

    private static HelpInformation getHelpInformationFor(CommandType.SubCommandType commandType) {
        switch (commandType) {
        case Filter:
            return new HelpInfoOfFilterUsage();
        case IndexFilter:
            return new HelpInfoOfIndexFilter();
        case TypeFilter:
            return new HelpInfoOfTypeFilter();
        case TimeFilter:
            return new HelpInfoOfTimeFilter();
        case StatusFilter:
            return new HelpInfoOfStatusFilter();
        default:
            return new HelpInformation();
        }
    }

    private static HelpInformation getHelpInformationFor(CommandType commandType,
                                                         CommandType.SubCommandType subCommandType) {
        switch (commandType) {
        case Add:
                return new HelpInfoOfAddCommand(subCommandType);
        default:
            return new HelpInformation();
        }
    }

    private static HelpInformation getHelpInformationFor() {
        return new HelpInformation();
    }
}
