public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the index-th task to be done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.markAsDone(index);
        storage.save(tasks);

        StringBuilder builder = new StringBuilder();
        builder.append("Nice! I've marked this task as done:\n");
        builder.append("  " + tasks.get(index) + "\n");
        return builder.toString();
    }

    /**
     * Explains DoneCommand.
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder("Command: done\n");
        builder.append("- Format: 'done {index}'.\n");
        builder.append("- Description: Marks the index-th task as done.\n");
        builder.append("- Example: 'done 1' command will mark the 1st task in the list as done.\n");

        return builder.toString();
    }
}
