package duke.command;

public class HelpCommand extends Command {

    private StringBuilder sb;

    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute() {
        sb = new StringBuilder("These are common Duke commands used in various situations");
        append(ListCommand.MESSAGE_USAGE);
        append(FindCommand.MESSAGE_USAGE);
        append(EventCommand.MESSAGE_USAGE);
        append(DeadlineCommand.MESSAGE_USAGE);
        append(ToDoCommand.MESSAGE_USAGE);
        append(DoneCommand.MESSAGE_USAGE);
        append(DeleteCommand.MESSAGE_USAGE);
        append(ByeCommand.MESSAGE_USAGE);
        return new CommandResult(sb.toString());
    }

    private void append(String message) {
        if (sb == null) {
            throw new IllegalStateException("StringBuilder has not been initialised yet");
        }
        sb.append(System.lineSeparator() + System.lineSeparator() + message);
    }
}
