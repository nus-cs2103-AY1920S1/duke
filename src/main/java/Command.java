public interface Command {
    /**
     * Returns whether this is the last command.
     * 
     * @return true if this is the last command
     */
    public boolean isExit();

    /**
     * Executes command.
     *
     * @param tasks List of task.
     * @param storage Storage to write datas to hard disk.
     * @throws DukeException if there is an invalid command.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException;
}
