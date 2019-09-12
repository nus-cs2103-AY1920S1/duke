public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * List down all the task in the list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        StringBuilder builder = new StringBuilder();

        builder.append("Here are the task in your list:\n");
        for (int i = 1; i <= tasks.size(); ++i) {
            builder.append(i + "." + tasks.get(i) + "\n");
        }

        return builder.toString();
    }

    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: list\n");
        builder.append("- Description: Shows all tasks.\n");

        return builder.toString();
    }
}
