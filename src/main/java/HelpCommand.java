public class HelpCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * List down all commands.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        StringBuilder builder = new StringBuilder("Here are the list of available commands:");

        builder.append("\n" + HelpCommand.getHelp());
        builder.append("\n" + ListCommand.getHelp());
        builder.append("\n" + ByeCommand.getHelp());
        builder.append("\n" + DoneCommand.getHelp());
        builder.append("\n" + DeleteCommand.getHelp());
        builder.append("\n" + FindCommand.getHelp());
        builder.append("\n" + TodoCommand.getHelp());
        builder.append("\n" + EventCommand.getHelp());
        builder.append("\n" + DeadlineCommand.getHelp());

        return builder.toString();
    }

    /**
     * Explains HelpCommand.
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: help\n");
        builder.append("- Description: Shows all duke commands.\n");

        return builder.toString();
    }
}
