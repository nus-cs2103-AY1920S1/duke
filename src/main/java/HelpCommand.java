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

        return builder.toString();
    }

    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: help\n");
        builder.append("- Description: Shows all duke commands.\n");

        return builder.toString();
    }
}
