package duke.commands;

/**
 * Implements the HelpCommand.
 * @author Lim Yong Shen, Kevin
 */
public class HelpCommand extends Command {

    private static final String LIST_OF_COMMANDS = "List of commands:\n"
            + "deadline command format: deadline <description> /by <date>\n"
            + "delete command format: delete <task number>\n"
            + "done command format: done <task number>\n"
            + "exit command format: bye\n"
            + "event command format: event <description> /at <dateAndTime>\n"
            + "find command format: find <keyword>\n"
            + "help command format: help\n"
            + "list command format: list\n"
            + "todo command format: todo <description>\n";
    public static final String COMMAND_WORD = "help";

    /**
     * Executes this help command and returns its command result.
     * @return This help command's command result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_OF_COMMANDS);
    }

}
