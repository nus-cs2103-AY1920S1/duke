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

        return builder.toString();
    }

    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: HELP\n");
        builder.append("- Description: Shows all duke commands.\n");
        return builder.toString();
    }
}
