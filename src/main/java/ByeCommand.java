public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints termination statement.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Explains ByeCommands.
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: bye\n");
        builder.append("- Description: This command terminates Duke.\n");

        return builder.toString();
    }
}
