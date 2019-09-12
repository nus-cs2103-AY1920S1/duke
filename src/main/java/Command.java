public abstract class Command {
    /**
     * Returns whether this is the last command.
     * 
     * @return true if this is the last command
     */
    public abstract boolean isExit();

    /**
     * Executes command.
     *
     * @param tasks List of task.
     * @param storage Storage to write datas to hard disk.
     * @throws DukeException if there is an invalid command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Explains the commands.
     *
     * @return Explanation of the commands.
     */
    public static String getHelp() {
        return "Help has not been provided.\n";
    }
}
