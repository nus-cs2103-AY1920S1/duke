public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Finds all task which description contains keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        StringBuilder builder = new StringBuilder();

        builder.append("Here are the matching tasks in your list:\n");
        int found = 0;
        for (int i = 1; i <= tasks.size(); ++i) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                found++;
                builder.append(found + "." + tasks.get(i) + "\n");
            }
        }

        return builder.toString();
    }

    /**
     * Explains FindCommand.
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: find\n");
        builder.append("- Format: 'find {keyword}'.\n");
        builder.append("- Description: Shows all task which description contains the keyword.\n");
        builder.append("- Example: 'find CS2103T Project'");
        builder.append(" will show all tasks with phrase 'CS2103T Project' in their description.\n");

        return builder.toString();
    }
}
